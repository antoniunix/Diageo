package com.chuys.gshp.shared.data.provider

import android.content.Context
import com.chuys.gshp.shared.data.repository.GeolocationDataRepository
import com.chuys.gshp.shared.domain.listener.LocationResultListener
import com.chuys.gshp.shared.domain.provider.GeolocationProvider

class GeolocationDataProvider(
    private val context: Context,
    private val locationResultListener: LocationResultListener
) :GeolocationProvider{

    private val geolocationDataRepository =GeolocationDataRepository(context)

    override fun getUserLocation() {
       return geolocationDataRepository.getLocation(locationResultListener)
    }



}