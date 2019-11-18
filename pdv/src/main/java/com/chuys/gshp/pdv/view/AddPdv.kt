package com.chuys.gshp.pdv.view

import android.location.Location
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.chuys.gshp.pdv.R
import com.chuys.gshp.pdv.presenter.PresenterAddPdv
import com.chuys.gshp.pdv.presenter.contract.AddPdvContract
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.chuys.gshp.shared.data.provider.ContextDataProvider
import com.chuys.gshp.shared.data.provider.GeolocationDataProvider
import com.chuys.gshp.shared.domain.provider.GeolocationProvider
import com.chuys.gshp.shared.presenter.GeolocationContract
import com.chuys.gshp.shared.util.extension.checkLocationPermission
import com.chuys.gshp.shared.util.extension.isGPSEnabled
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class AddPdv :FragmentActivity (), OnMapReadyCallback, AddPdvContract.AddPdvViewContract,GeolocationContract.GeolocationViewContract{
    override fun setUserLocation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var geolocationProvider: GeolocationProvider
    private lateinit var presenter: AddPdvContract.AddPdvPresenterContract
    private lateinit var mMap: GoogleMap
    private var latLngMex=LatLng(19.4284706,-99.1276627)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pdv)
        val mapFragment= supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        init()
    }

    fun init(){
        val contextProvider=ContextDataProvider(this)
        geolocationProvider=GeolocationDataProvider(JobExecutor(), UIThread(),contextProvider)
        if(this.isGPSEnabled() && this.checkLocationPermission()){
            presenter = PresenterAddPdv(this,this, geolocationProvider)
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        if (map != null) {
            mMap= map
            mMap.setMinZoomPreference(6.0f)
            mMap.setMaxZoomPreference(14.0f)
            mMap.isMyLocationEnabled=true
            presenter.getUserLocation()
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngMex,16f))
        }
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLocation(location: Location) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),16f))
    }

}