package com.chuys.gshp.executables.data.provider

import com.chuys.gshp.executables.data.repository.ExecutableDataRepository
import com.chuys.gshp.executables.domain.model.ExecutableData
import com.chuys.gshp.executables.domain.provider.ExecutableProvider
import com.chuys.gshp.executables.domain.usescases.ExecutableUseCase
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

class ExecutableDataProvider (
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread
) : ExecutableProvider {

    private val repository = ExecutableDataRepository()
    override fun getExecutableUseCase(): SingleUseCase<Any, Resource<List<ExecutableData>>> {
        return ExecutableUseCase(repository, jobExecutor, uiThread)
    }

}