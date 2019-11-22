package com.chuys.gshp.shared.domain.provider

import android.location.Address
import android.location.Location
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface GeolocationProvider {

    fun getUserLocation():SingleUseCase<Any,Location>

    fun getAddres(lat: Double, lon: Double):SingleUseCase<Any, Address>

}