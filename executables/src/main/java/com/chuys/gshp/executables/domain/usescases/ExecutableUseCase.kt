package com.chuys.gshp.executables.domain.usescases

import com.chuys.gshp.executables.domain.model.ExecutableData
import com.chuys.gshp.executables.domain.repository.ExecutableRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import io.reactivex.Single

class ExecutableUseCase constructor(
    private val executableRepository: ExecutableRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Any, Resource<List<ExecutableData>>>(
    threadExecutor,
    postExecutionThread
) {
    override fun buildUseCase(params: Any?): Single<Resource<List<ExecutableData>>> {
        return executableRepository.getExecutableToMesure()
    }
}