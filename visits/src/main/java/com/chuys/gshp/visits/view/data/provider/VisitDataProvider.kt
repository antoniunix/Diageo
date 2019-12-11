package com.chuys.gshp.visits.view.data.provider

import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import com.chuys.gshp.visits.view.data.repository.VisitsDataRepository
import com.chuys.gshp.visits.view.domain.model.VisitModel
import com.chuys.gshp.visits.view.domain.provider.VisitProvider
import com.chuys.gshp.visits.view.domain.usecase.GetVisitsUseCase

class VisitDataProvider(
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread
) : VisitProvider {
    private val repository = VisitsDataRepository()
    override fun getVisitUseCase(): SingleUseCase<Any, Resource<List<VisitModel>>> {
        return GetVisitsUseCase(repository, jobExecutor, uiThread)
    }

}