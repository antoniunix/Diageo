package com.chuys.gshp.pdv.data.repository

import android.util.Log
import com.chuys.gshp.pdv.domain.model.PdvData
import com.chuys.gshp.pdv.domain.repository.PdvRepository
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single


class PdvDataRepository : PdvRepository {

    override fun getAllPdv(): Single<Resource<List<PdvData>>> {
        //This method must change by realtime
        val pdvData = PdvData(
            1, "", 1,
            "", "", "CODEE", 1,
            "", "", "",
            "", "", "", 0.0, 0.0
        )
        return Single.just(listOf(pdvData)).map {
            result->
            when{ !result.isEmpty()->{
                return@map Resource.success(result,"")
            }else-> Resource.error<List<PdvData>>("Lista Vacia")

            }
        }
    }

}

