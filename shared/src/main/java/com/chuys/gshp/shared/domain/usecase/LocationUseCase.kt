package com.chuys.gshp.shared.domain.usecase

import android.location.Location
import com.chuys.gshp.shared.data.provider.ContextDataProvider
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.repository.GeolocationRepository
import io.reactivex.Single

class LocationUseCase constructor(
    private val geolocationRepository : GeolocationRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread):SingleUseCase<Any,Location>(threadExecutor,postExecutionThread){

    override fun buildUseCase(params: Any?): Single<Location> {
        return geolocationRepository.getLocation()
    }


}