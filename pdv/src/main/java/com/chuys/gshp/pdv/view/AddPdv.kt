package com.chuys.gshp.pdv.view

import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.chuys.gshp.pdv.R
import com.chuys.gshp.pdv.presenter.PresenterAddPdv
import com.chuys.gshp.pdv.presenter.contract.AddPdvContract
import com.chuys.gshp.shared.data.provider.GeolocationDataProvider
import com.chuys.gshp.shared.domain.listener.LocationResultListener
import com.chuys.gshp.shared.domain.provider.GeolocationProvider
import com.chuys.gshp.shared.presenter.GeolocationContract
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class AddPdv :FragmentActivity (), OnMapReadyCallback, AddPdvContract.AddPdvViewContract,GeolocationContract.GeolocationViewContract,
    LocationResultListener{
    private lateinit var geolocationProvider: GeolocationProvider
    private lateinit var presenter: AddPdvContract.AddPdvPresenterContract
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pdv)
        init()
        val mapFragment= supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

    }

    fun init(){
        geolocationProvider=GeolocationDataProvider(this,this)
        presenter = PresenterAddPdv(this,this, geolocationProvider)

    }

    override fun onMapReady(map: GoogleMap?) {
        if (map != null) {
            mMap= map
            mMap.setMinZoomPreference(6.0f)
            mMap.setMaxZoomPreference(14.0f)
        }
      map?.isMyLocationEnabled=true
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLocation(location: Location) {

    }

    override fun onResume() {
        super.onResume()
        presenter.getUserLocation()
    }

    override fun locationResult(location: Location) {
       mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(location.latitude,location.longitude), 0.6F))
    }
}