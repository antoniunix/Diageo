package com.chuys.gshp.sku.data.repository

import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import com.chuys.gshp.sku.domain.repository.AvailabilityAndPriceRepository
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

class AvailabilityAndPriceDataRepository : AvailabilityAndPriceRepository {
    override fun getSkuToMesureAvailabilityAndPrice(): Single<Resource<List<SkuAvailabilityAndPriceData>>> {
        val sku = SkuAvailabilityAndPriceData("1", 1, "Bukanans", "Ron", 1, true, 0.0f, false)
        val sku1 = SkuAvailabilityAndPriceData("2", 1, "capitan", "tequila", 2, true, 0.0f, false)
        return Single.just(listOf(sku, sku1)).map { result ->
            when {
                !result.isEmpty() -> {
                    return@map Resource.success(result, StringConstant.EMPTY_STRING)
                }
                else -> Resource.error<List<SkuAvailabilityAndPriceData>>(StringConstant.DONT_HAVE_DATA_IN_DATABASE_ESP)

            }
        }
    }


    override fun saveAvailabilityAndPrice() {

    }

}