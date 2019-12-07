package com.chuys.gshp.shared.domain.usecase

import android.location.Address
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.repository.GeolocationRepository
import io.reactivex.Single

class AddressUseCase constructor(
    private val geolocationRepository: GeolocationRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val lat: Double, private val lon: Double
)  :SingleUseCase<Any,Address>(threadExecutor,postExecutionThread){
    override fun buildUseCase(params: Any?): Single<Address> {
        return geolocationRepository.getAddress(lat,lon)
    }
}