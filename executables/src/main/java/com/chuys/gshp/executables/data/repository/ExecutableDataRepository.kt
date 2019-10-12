package com.chuys.gshp.executables.data.repository

import com.chuys.gshp.executables.domain.model.ExecutableData
import com.chuys.gshp.executables.domain.repository.ExecutableRepository
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

class ExecutableDataRepository() : ExecutableRepository {
    override fun getExecutableToMesure(): Single<Resource<List<ExecutableData>>> {
        val exe = ExecutableData(
            "1",
            1,
            "Limpiar exhibidor",
            "debe quedar super limpio el exhibidor",
            1,
            false,
            false
        )
        val exe2 = ExecutableData(
            "2",
            2,
            "poner POP",
            "Se debe poner un material pop a la entrada",
            2,
            false,
            false
        )
        return Single.just(listOf(exe, exe2)).map { result ->
            when {
                !result.isEmpty() -> {
                    return@map Resource.success(result, StringConstant.EMPTY_STRING)
                }
                else -> Resource.error<List<ExecutableData>>(StringConstant.DONT_HAVE_DATA_IN_DATABASE_ESP)

            }
        }
    }

    override fun saveExecutable() {
    }

}