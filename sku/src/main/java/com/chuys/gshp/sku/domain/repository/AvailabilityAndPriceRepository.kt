package com.chuys.gshp.sku.domain.repository

import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

interface AvailabilityAndPriceRepository {
    fun getSkuToMesureAvailabilityAndPrice(): Single<Resource<List<SkuAvailabilityAndPriceData>>>
    fun saveAvailabilityAndPrice()
}