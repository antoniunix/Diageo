package com.chuys.gshp.sku.domain.usescases

import android.util.Log
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import com.chuys.gshp.sku.domain.repository.AvailabilityAndPriceRepository
import io.reactivex.Single

class SaveReportSkuUseCase constructor(
    private val availabilityAndPriceRepository: AvailabilityAndPriceRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<SkuAvailabilityAndPriceData>, Resource<Boolean>>(
    threadExecutor,
    postExecutionThread
) {
    override fun buildUseCase(params: List<SkuAvailabilityAndPriceData>?): Single<Resource<Boolean>> {
        return availabilityAndPriceRepository.saveAvailabilityAndPrice(params)
    }
}