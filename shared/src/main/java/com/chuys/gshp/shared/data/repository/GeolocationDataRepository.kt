package com.chuys.gshp.shared.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import android.os.Looper
import com.chuys.gshp.shared.domain.listener.LocationResultListener
import com.google.android.gms.location.*
import io.reactivex.Single


class GeolocationDataRepository(private val context: Context) {

    private var fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(context)

    fun getLocation(locationResultListener: LocationResultListener) {
        System.out.println("Init georepository")
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
                locationResultListener.locationResult(location)
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

    fun exampe():Single<Location>{
        return Single.create{single ->
            fusedLocationProviderClient.lastLocation.addOnCompleteListener{
                val location = it.result
                if (location == null) {
                   single.onError(Exception(""))
                }else{
                    single.onSuccess(location)
                }
            }.addOnCanceledListener {
                single.onError(Exception(""))
            }
        }

    }



}
