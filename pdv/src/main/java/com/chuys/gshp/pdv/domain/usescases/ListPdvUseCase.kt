package com.chuys.gshp.pdv.domain.usescases

import com.chuys.gshp.pdv.domain.model.PdvModel
import com.chuys.gshp.pdv.domain.repository.PdvRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import io.reactivex.Single

class ListPdvUseCase constructor(
    private val pdvRepository: PdvRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Any, Resource<List<PdvModel>>>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: Any?): Single<Resource<List<PdvModel>>> {
        return pdvRepository.getAllPdv()
    }
}