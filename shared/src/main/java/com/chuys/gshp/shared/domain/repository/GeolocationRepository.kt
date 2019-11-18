package com.chuys.gshp.shared.domain.repository

import android.location.Location
import io.reactivex.Single

interface GeolocationRepository {
    fun getLocation(): Single<Location>
}