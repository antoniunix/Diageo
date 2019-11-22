package com.chuys.gshp.shared.domain.usecase

import com.chuys.gshp.shared.data.model.Form
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.repository.RealDBRepository
import io.reactivex.Single

class RealDBUseCase(
    private val realDBRepository: RealDBRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Any, List<Form>>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: Any?): Single<List<Form>> {
        return realDBRepository.getData()
    }
}