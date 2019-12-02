package com.chuys.gshp.pdv.data.provider

import com.chuys.gshp.pdv.data.repository.KpiDataRepository
import com.chuys.gshp.pdv.data.model.KpiData
import com.chuys.gshp.pdv.domain.providers.KpiProvider
import com.chuys.gshp.pdv.domain.usescases.KpiUseCase
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

class KpiDataProvider(
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread
) : KpiProvider {

    private val kpiRepository = KpiDataRepository()

    override fun getData(): SingleUseCase<String, Resource<KpiData>> {
        return KpiUseCase(kpiRepository, jobExecutor, uiThread)
    }
}