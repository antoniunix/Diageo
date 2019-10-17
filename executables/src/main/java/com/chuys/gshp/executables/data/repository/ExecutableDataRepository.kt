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
            0,
            false,
            2,0
        )
        val exe2 = ExecutableData("2",2,"poner POP","Se debe poner un material pop a la entrada",2,
            0,false,2,0
        )
        val exe3 = ExecutableData("3",3,"poner POP","Se debe poner un material pop a la entrada",2,
            0,false,2,0
        )
        val exe4 = ExecutableData("4",4,"poner POP","Se debe poner un material pop a la entrada",2,
            0,false,2,0
        )
        val exe5 = ExecutableData("5",5,"poner POP","Se debe poner un material pop a la entrada",2,
            0,false,2,0
        )
        val exe6 = ExecutableData("6",6,"poner POP","Se debe poner un material pop a la entrada",2,
            0,false,2,0
        )
        val exe7 = ExecutableData("7",7,"poner POP","Se debe poner un material pop a la entrada",2,
            0,false,2,0
        )
        val exe8 = ExecutableData("8",8,"poner POP","Se debe poner un material pop a la entrada",2,
            0,false,2,0
        )
        val exe9 = ExecutableData("9",9,"poner POP","Se debe poner un material pop a la entrada",2,
            0,false,2,0
        )
        val exe10 = ExecutableData("10",10,"poner POP","Se debe poner un material pop a la entrada",2,
            0,false,2,0
        )
        val exe11 = ExecutableData("11",11,"poner POP","Se debe poner un material pop a la entrada",2,
            0,false,2,0
        )
        val exe12 = ExecutableData("12",12,"poner POP","Se debe poner un material pop a la entrada",2,
            0,false,2,0
        )
        return Single.just(listOf(exe, exe2, exe3, exe4, exe5, exe6, exe7, exe8, exe9, exe10, exe11, exe12)).map { result ->
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