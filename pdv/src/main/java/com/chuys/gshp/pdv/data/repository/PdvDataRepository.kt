package com.chuys.gshp.pdv.data.repository

import com.chuys.gshp.pdv.domain.model.PdvData
import com.chuys.gshp.pdv.domain.repository.PdvRepository
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single


class PdvDataRepository : PdvRepository {

    override fun getAllPdv(): Single<Resource<List<PdvData>>> {
        //This method must change by realtime
        val pdvData = PdvData(
            1, "PDV1", 1,
            "CLIENTE1", "", "CODEE", 1,
            "", "", "",
            "", "", "", 0.0, 0.0
        )
        val pdvData2 = PdvData(
            2, "PDV2", 2,
            "CLIENTE2", "", "CODEE", 1,
            "", "", "",
            "", "", "", 0.0, 0.0
        )
        return Single.just(listOf(pdvData,pdvData2)).map { result ->
            when {
                !result.isEmpty() -> {
                    return@map Resource.success(result, StringConstant.EMPTY_STRING)
                }
                else -> Resource.error<List<PdvData>>(StringConstant.DONT_HAVE_DATA_IN_DATABASE_ESP)

            }
        }
    }

}

