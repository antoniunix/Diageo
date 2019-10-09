package com.chuys.gshp.sku.domain.usescases

import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import com.chuys.gshp.sku.domain.repository.AvailabilityAndPriceRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import io.reactivex.Single

class GetSkuToMeasurementAvailabilityAndPriceUseCase constructor(
    private val availabilityAndPriceRepository: AvailabilityAndPriceRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Any, Resource<List<SkuAvailabilityAndPriceData>>>(
    threadExecutor,
    postExecutionThread
) {
    override fun buildUseCase(params: Any?): Single<Resource<List<SkuAvailabilityAndPriceData>>> {
        return availabilityAndPriceRepository.getSkuToMesureAvailabilityAndPrice()
    }
}