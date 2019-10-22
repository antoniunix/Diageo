package com.chuys.gshp.shared.data.repository

import android.app.Application
import android.location.LocationManager
import android.os.Looper
import com.google.android.gms.location.*


class GeolocationRepository {

    fun getLocation(application: Application) {
         lateinit var fusedLocationProviderClient: FusedLocationProviderClient
        lateinit var locationManager: LocationManager
        lateinit var locationRequest: LocationRequest
        lateinit var locationCallback: LocationCallback

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(application)
        fusedLocationProviderClient.setMockMode(false)
        locationRequest = LocationRequest
            .create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(5000)
            .setFastestInterval(0)


        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                fusedLocationProviderClient.removeLocationUpdates(locationCallback)
            }
        }

        fusedLocationProviderClient.lastLocation.addOnCompleteListener {
            val location = it.result
            if (location == null){
                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
            }else{
                //locationResultListener.getLocation(location)
               System.out.println("dale "+location.latitude+" "+location.longitude)
            }
        }
    }
}