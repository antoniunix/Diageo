package com.chuys.gshp.sku.data.provider

import com.chuys.gshp.sku.data.repository.AvailabilityAndPriceDataRepository
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import com.chuys.gshp.sku.domain.provider.AvailabilityAndPriceProvider
import com.chuys.gshp.sku.domain.usescases.GetSkuToMeasurementAvailabilityAndPriceUseCase
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

class AvailabilityAndPriceDataProvider(
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread
) : AvailabilityAndPriceProvider {

    private val repository = AvailabilityAndPriceDataRepository()
    override fun getSkuToMeasurementAvailabilityAndPriceUseCase(): SingleUseCase<Any, Resource<List<SkuAvailabilityAndPriceData>>> {
        return GetSkuToMeasurementAvailabilityAndPriceUseCase(repository, jobExecutor, uiThread)
    }

}