package com.chuys.gshp.sku.domain.provider

import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface AvailabilityAndPriceProvider {
    fun getSkuToMeasurementAvailabilityAndPriceUseCase(): SingleUseCase<Any, Resource<List<SkuAvailabilityAndPriceData>>>
    fun saveReportSku() : SingleUseCase<List<SkuAvailabilityAndPriceData>, Resource<Boolean>>
}