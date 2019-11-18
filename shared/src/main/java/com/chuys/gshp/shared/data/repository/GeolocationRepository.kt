package com.chuys.gshp.shared.data.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import android.os.Looper
import com.google.android.gms.location.*


class GeolocationRepository(
    private val context: Context
) {

    fun getLocation() {
        System.out.println("Init georepository")
        lateinit var fusedLocationProviderClient: FusedLocationProviderClient
        lateinit var locationRequest: LocationRequest
        lateinit var locationCallback: LocationCallback
        var sharedPreferences : SharedPreferences

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
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
            if (location == null) {
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.myLooper()
                )
            } else {
                System.out.println("Init georepository1")
                //locationResultListener.getLocation(location)
               // System.out.println("dale " + location.latitude + " " + location.longitude)
                sharedPreferences= context.getSharedPreferences(
                   "location", Context.MODE_PRIVATE)
                var tmp :String
                var locationText : String = "location " + location.latitude + " " + location.longitude
                tmp=sharedPreferences.getString("location","")
                sharedPreferences.edit().putString("location",tmp+" "+locationText).commit()

            }
        }
    }
}