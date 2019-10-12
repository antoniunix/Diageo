package com.chuys.gshp.executables.domain.repository

import com.chuys.gshp.executables.domain.model.ExecutableData
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

interface ExecutableRepository {
    fun getExecutableToMesure(): Single<Resource<List<ExecutableData>>>
    fun saveExecutable()
}