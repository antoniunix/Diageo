package com.chuys.gshp.shared.domain.repository

import android.location.Address
import android.location.Location
import io.reactivex.Single

interface GeolocationRepository {
    fun getLocation(): Single<Location>

    fun getAddress(lat:Double,lon:Double): Single<Address>

}