package com.chuys.gshp.geolocation.domain.repository

import android.app.Application
import com.chuys.gshp.geolocation.utility.extentions.checkLocationcheckLocationPermission
import com.chuys.gshp.geolocation.utility.extentions.isGPSEnabled
import com.google.android.gms.location.LocationServices


class LocationRepository(private val application: Application){

    fun getLocation(){
        if(application.isGPSEnabled() && application.checkLocationcheckLocationPermission()){
            LocationServices.getFusedLocationProviderClient(application)
                ?.lastLocation
                ?.addOnSuccessListener{location:android.location.Location?->
                   /* if (location!=null)*/
                        //Logica de insercion o envio
                }
        }
    }

}