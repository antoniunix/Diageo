package com.chuys.gshp.pdv.domain.usescases

import com.chuys.gshp.pdv.data.model.KpiData
import com.chuys.gshp.pdv.domain.repository.KpiRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import io.reactivex.Single

class KpiUseCase constructor(
    private val kpiRepository: KpiRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<String, Resource<KpiData>>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: String?): Single<Resource<KpiData>> {
        return kpiRepository.getData(params)
    }
}