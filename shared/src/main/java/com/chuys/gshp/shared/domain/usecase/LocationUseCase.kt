package com.chuys.gshp.shared.domain.usecase

import android.location.Location
import com.chuys.gshp.shared.domain.repository.GeolocationRepository

class LocationUseCase constructor(
    private val geolocationRepository : GeolocationRepository){

     fun buildUseCase(): Location {
        return geolocationRepository.getLocation()
    }
}