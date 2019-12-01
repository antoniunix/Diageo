package com.chuys.gshp.pdv.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.chuys.gshp.pdv.R
import com.chuys.gshp.pdv.presenter.contract.CheckContract
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.chuys.gshp.shared.data.provider.ContextDataProvider
import com.chuys.gshp.shared.data.provider.GeolocationDataProvider
import com.chuys.gshp.shared.domain.constant.IntConstants
import com.chuys.gshp.shared.domain.provider.GeolocationProvider
import com.chuys.gshp.shared.presenter.GeolocationContract
import com.chuys.gshp.shared.util.extension.checkLocationPermission
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker

class CheckInOut :FragmentActivity(), OnMapReadyCallback,
        CheckContract.CheckViewContract, GeolocationContract.GeolocationViewContract{


    private lateinit var mapFragment: SupportMapFragment
    private lateinit var geolocationProvider: GeolocationProvider
    private lateinit var pdvMarker : Marker
    private lateinit var mMap: GoogleMap
    private val TAG = "CheckInOut"
    private lateinit var presenter:CheckContract.CheckPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)
        mapFragment= supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        init()
    }

    private fun init(){
        if(this.checkLocationPermission()){
            val contextProvider= ContextDataProvider(this)
            geolocationProvider= GeolocationDataProvider(JobExecutor(), UIThread(),contextProvider)
           // presenter = PresenterAddPdv(this,this, geolocationProvider)
            mapFragment.getMapAsync(this)
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), IntConstants.LOCATION_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == IntConstants.LOCATION_ACTIVITY_REQUEST_CODE) {
            finish()
            intent = Intent(this, CheckInOut::class.java)
            startActivity(intent)
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
            //pdvMarker=mMap.addMarker(MarkerOptions().position(latLngMex).draggable(true))
            presenter.getUserLocation()

        }
    }

    override fun showLocation(location: Location) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAddres(address: Address) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}