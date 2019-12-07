package com.chuys.gshp.pdv.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.chuys.gshp.pdv.R
import com.chuys.gshp.pdv.presenter.PresenterAddPdv
import com.chuys.gshp.pdv.presenter.contract.AddPdvContract
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.chuys.gshp.shared.data.provider.ContextDataProvider
import com.chuys.gshp.shared.data.provider.GeolocationDataProvider
import com.chuys.gshp.shared.domain.constant.DoubleConstants
import com.chuys.gshp.shared.domain.constant.IntConstants
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
import kotlinx.android.synthetic.main.activity_new_pdv.*

class AddPdv :FragmentActivity (), OnMapReadyCallback,
    AddPdvContract.AddPdvViewContract,GeolocationContract.GeolocationViewContract, GoogleMap.OnMarkerDragListener{



    private lateinit var geolocationProvider: GeolocationProvider
    private lateinit var presenter: AddPdvContract.AddPdvPresenterContract
    private lateinit var mMap: GoogleMap
    private val latLngMex=LatLng(DoubleConstants.latMex,DoubleConstants.lonMex)
    private lateinit var pdvMarker : Marker
    private val TAG = "AddPdv"
    private lateinit var mapFragment:SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pdv)
        mapFragment= supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        init()

    }

    private fun init(){
        if(this.checkLocationPermission()){
            val contextProvider=ContextDataProvider(this)
            geolocationProvider=GeolocationDataProvider(JobExecutor(), UIThread(),contextProvider)
            presenter = PresenterAddPdv(this,this, geolocationProvider)
            mapFragment.getMapAsync(this)
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), IntConstants.LOCATION_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == IntConstants.LOCATION_ACTIVITY_REQUEST_CODE) {
            finish()
           intent = Intent(this, AddPdv::class.java)
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
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngMex,16f))
            pdvMarker=mMap.addMarker(MarkerOptions().position(latLngMex).draggable(true))
            mMap.setOnMarkerDragListener(this)
            presenter.getUserLocation()

        }
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLocation(location: Location) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),16f))
        pdvMarker.remove()
        pdvMarker=mMap.addMarker(MarkerOptions().position(LatLng(location.latitude,location.longitude)).draggable(true))
    }

    override fun setAddres(address: Address) {
        edt_input_address.setText(address.getAddressLine(0))
        edt_input_delegation.setText(address.locality)
        edt_input_suburb.setText(address.subLocality)
    }

    override fun onMarkerDragEnd(p0: Marker?) {
       Log.e(TAG,"position "+p0?.position?.latitude+ " "+p0?.position?.longitude)
        presenter.getAddress(p0!!.position.latitude,p0.position.longitude)
    }

    override fun onMarkerDragStart(p0: Marker?) {
        Log.e(TAG,"onMarkerDragStart ")
    }

    override fun onMarkerDrag(p0: Marker?) {
        Log.e(TAG,"onMarkerDrag ")
    }

    override fun setUserLocation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
