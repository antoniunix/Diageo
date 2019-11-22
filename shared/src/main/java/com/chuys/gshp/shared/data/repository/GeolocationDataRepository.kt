package com.chuys.gshp.shared.data.repository

import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.chuys.gshp.shared.domain.provider.ContextProvider
import com.chuys.gshp.shared.domain.repository.GeolocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.Single
import java.util.*


class GeolocationDataRepository(private val context: ContextProvider) : GeolocationRepository {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun getLocation(): Single<Location> {
        initFusedLocationProvider()
        return initLocation()
    }


    override fun getAddress(lat:Double,lon:Double): Single<Address> {
       return initAddress(lat,lon)
    }

    private fun initFusedLocationProvider() {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context.getContext())
        fusedLocationProviderClient.setMockMode(false)
    }

    private fun initLocation(): Single<Location> {
        return Single.create { single ->
            fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                val location = it.result
                if (location == null) {
                    single.onError(Exception(""))
                } else {
                    single.onSuccess(location)
                }
            }.addOnCanceledListener { single.onError(Exception("")) }
        }
    }

    private fun initAddress(lat: Double, lon: Double): Single<Address> {
        val geocoder = initGeocoder()
        lateinit var address :List<Address>
        return  Single.create { result->
            address=geocoder.getFromLocation(lat,lon,1)
            if(address.isNotEmpty()){
               result.onSuccess(address.get(0))
            }else{
                result.onError(Exception(""))
            }

        }
    }

    fun initGeocoder(): Geocoder {
        return Geocoder(context.getContext(), Locale.getDefault())
    }


}
