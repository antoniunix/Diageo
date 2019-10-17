package com.chuys.gshp.sku.data.repository

import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import com.chuys.gshp.sku.domain.repository.AvailabilityAndPriceRepository
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

class AvailabilityAndPriceDataRepository : AvailabilityAndPriceRepository {
    override fun getSkuToMesureAvailabilityAndPrice(): Single<Resource<List<SkuAvailabilityAndPriceData>>> {
        val sku = SkuAvailabilityAndPriceData("1", 1, "Bukanans", "Ron", 1, 0, 0.0f, false)
        val sku1 = SkuAvailabilityAndPriceData("2", 1, "capitan", "tequila", 2, 0, 0.0f, false)
        val sku2 = SkuAvailabilityAndPriceData("3", 1, "cerveza", "tequila", 3, 0, 0.0f, false)
        val sku3 = SkuAvailabilityAndPriceData("4", 1, "RON", "tequila", 4, 0, 0.0f, false)
        val sku4 = SkuAvailabilityAndPriceData("5", 1, "indio", "tequila", 5, 0, 0.0f, false)
        val sku5 = SkuAvailabilityAndPriceData("6", 1, "corona", "tequila", 6, 0, 0.0f, false)
        val sku6 = SkuAvailabilityAndPriceData("7", 1, "modelo", "tequila", 7, 0, 0.0f, false)
        val sku7 = SkuAvailabilityAndPriceData("8", 1, "vodka", "tequila", 8, 0, 0.0f, false)
        val sku8 = SkuAvailabilityAndPriceData("9", 1, "carta blanca", "tequila", 9, 0, 0.0f, false)
        val sku9 = SkuAvailabilityAndPriceData("10", 1, "alcohol", "tequila", 10, 0, 0.0f, false)
        val sku10 = SkuAvailabilityAndPriceData("11", 1, "XX", "tequila", 11, 0, 0.0f, false)
        return Single.just(listOf(sku, sku1,sku2,sku3,sku4,sku5,sku6,sku7,sku8,sku9,sku10)).map { result ->
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