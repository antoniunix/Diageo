package com.chuys.gshp.pdv.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.annotation.UiThread
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.pdv.R
import com.chuys.gshp.pdv.data.provider.CheckDataProvider
import com.chuys.gshp.pdv.data.provider.ReportDataProvider
import com.chuys.gshp.pdv.domain.model.PdvModel
import com.chuys.gshp.pdv.domain.providers.CheckProvider
import com.chuys.gshp.pdv.domain.providers.ReportProvider
import com.chuys.gshp.pdv.presenter.PresenterCheck
import com.chuys.gshp.pdv.presenter.contract.CheckContract
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.chuys.gshp.shared.data.provider.ContextDataProvider
import com.chuys.gshp.shared.data.provider.GeolocationDataProvider
import com.chuys.gshp.shared.domain.constant.IntConstants
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.provider.GeolocationProvider
import com.chuys.gshp.shared.presenter.GeolocationContract
import com.chuys.gshp.shared.util.extension.checkLocationPermission
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_check.*

class CheckInOut :FragmentActivity(), OnMapReadyCallback,
        CheckContract.CheckViewContract, GeolocationContract.GeolocationViewContract, View.OnClickListener{

    private lateinit var mapFragment: SupportMapFragment
    private lateinit var geolocationProvider: GeolocationProvider
    private lateinit var checkProvider: CheckProvider
    private lateinit var reportProvider: ReportProvider
    private lateinit var pdvMarker : Marker
    private lateinit var mMap: GoogleMap
    private val TAG = "CheckInOut"
    private lateinit var presenter:CheckContract.CheckPresenterContract
    private lateinit var pdvbundle:PdvModel
    var typeCheckInOut:Int = 0
    private lateinit var latLngPdv: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)
        mapFragment= supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        pdvbundle=intent.extras.getParcelable<PdvModel>(StringConstant.KEYBUNDLE)
        typeCheckInOut=intent.extras.getInt(StringConstant.CHECKBUNDLE)
        initPermission()
        initView()
    }

    private fun initView(){
        txt_pdv_name.setText(pdvbundle.name)
        txt_address.setText(pdvbundle.address)

        when(typeCheckInOut){
            IntConstants.CHECKIN-> btn_init_check.setText(getString(R.string.checkin_btn))
            IntConstants.CHECKOUT-> btn_init_check.setText(getString(R.string.checkout_btn))
        }
        btn_init_check.setOnClickListener(this)

    }

    private fun initPermission(){
        if(this.checkLocationPermission()){
            val contextProvider= ContextDataProvider(this)
            geolocationProvider= GeolocationDataProvider(JobExecutor(), UIThread(),contextProvider)
            checkProvider= CheckDataProvider(JobExecutor(),UIThread())
            reportProvider=ReportDataProvider(JobExecutor(),UIThread())
            presenter = PresenterCheck(this,this, geolocationProvider,checkProvider,reportProvider)
            mapFragment.getMapAsync(this)
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), IntConstants.LOCATION_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == IntConstants.LOCATION_ACTIVITY_REQUEST_CODE) {
            finish()
            val bundle = Bundle()
            bundle.putInt(StringConstant.CHECKBUNDLE,typeCheckInOut)
            bundle.putParcelable(StringConstant.KEYBUNDLE,pdvbundle)
            ActivityManager.changeToActivitywithBundle(Activities.CHECK, this, bundle)

        }
    }

    @SuppressLint("MissingSuperCall")
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == IntConstants.LOCATION_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                showEnableLocationDialog()
            }
        }
    }

    private fun showEnableLocationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(resources.getString(R.string.enabled_location))
            .setCancelable(false)
            .create()
            .show()
    }

    override fun onMapReady(map: GoogleMap?) {
        if (map != null) {
            mMap= map
            mMap.setMinZoomPreference(6.0f)
            mMap.setMaxZoomPreference(14.0f)
            mMap.isMyLocationEnabled=true
            latLngPdv=LatLng(pdvbundle.lat,pdvbundle.lon)
            pdvMarker=mMap.addMarker(MarkerOptions().position(latLngPdv))
            presenter.getUserLocation()

        }
    }

    override fun showLocation(location: Location) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),16f))
    }

    override fun setAddres(address: Address) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onClick(v: View?) {
        val bundle:Bundle= Bundle()
        when (v?.id){
            R.id.btn_init_check->{
                val type=if(typeCheckInOut==IntConstants.CHECKIN) IntConstants.CHECKOUT else IntConstants.CHECKIN
                bundle.putInt(StringConstant.CHECKBUNDLE,type)
                bundle.putParcelable(StringConstant.KEYBUNDLE,pdvbundle)
                presenter.saveReportReport(pdvbundle.id.toLong())
                presenter.saveCheckReport(this,bundle,type)
            }
        }

    }

}