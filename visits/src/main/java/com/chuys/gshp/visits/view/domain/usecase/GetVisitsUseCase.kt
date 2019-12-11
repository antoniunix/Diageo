package com.chuys.gshp.visits.view.domain.usecase

import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import com.chuys.gshp.visits.view.data.repository.VisitsDataRepository
import com.chuys.gshp.visits.view.domain.model.VisitModel
import io.reactivex.Single

class GetVisitsUseCase constructor(
    private val visitsDataRepository: VisitsDataRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Any, Resource<List<VisitModel>>>(
    threadExecutor,
    postExecutionThread
) {
    override fun buildUseCase(params: Any?): Single<Resource<List<VisitModel>>> {
        return visitsDataRepository.getPdvVisited()
    }
}