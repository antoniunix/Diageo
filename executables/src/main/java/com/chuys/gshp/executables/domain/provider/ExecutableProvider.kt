package com.chuys.gshp.executables.domain.provider

import com.chuys.gshp.executables.domain.model.ExecutableData
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface ExecutableProvider {
    fun getExecutableUseCase(): SingleUseCase<Any, Resource<List<ExecutableData>>>
}