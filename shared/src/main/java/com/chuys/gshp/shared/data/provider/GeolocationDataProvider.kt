package com.chuys.gshp.shared.data.provider

import android.location.Address
import android.location.Location
import com.chuys.gshp.shared.data.repository.GeolocationDataRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.provider.GeolocationProvider
import com.chuys.gshp.shared.domain.usecase.AddressUseCase
import com.chuys.gshp.shared.domain.usecase.LocationUseCase
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

class GeolocationDataProvider(
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread,
    private val contextDataProvider: ContextDataProvider
) : GeolocationProvider {
    private val geolocationDataRepository = GeolocationDataRepository(contextDataProvider)

    override fun getUserLocation(): SingleUseCase<Any, Location> {
        return LocationUseCase(geolocationDataRepository, jobExecutor, uiThread)
    }

    override fun getAddres(lat: Double, lon: Double): SingleUseCase<Any, Address> {
        return AddressUseCase(geolocationDataRepository, jobExecutor, uiThread,lat,lon)
    }



}