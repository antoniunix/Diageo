package com.chuys.gshp.shared.data.repository

import android.content.Context
import android.location.Location
import com.chuys.gshp.shared.domain.repository.GeolocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.Single


class GeolocationDataRepository(private val context: Context): GeolocationRepository {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    fun initFusedLocationProvider(){
        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(context)
        fusedLocationProviderClient.setMockMode(false)
    }

    fun initLocation():Single<Location>{
        return Single.create {single->
            fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                val location= it.result
                if(location==null){
                   single.onError(Exception(""))
                }else{
                    single.onSuccess(location)
                }
            }.addOnCanceledListener { single.onError(Exception(""))  }
        }
    }

    override fun getLocation(): Single<Location> {
       initFusedLocationProvider()
        return initLocation()
    }
}
