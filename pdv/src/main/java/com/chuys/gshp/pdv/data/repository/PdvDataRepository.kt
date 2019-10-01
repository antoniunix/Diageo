package com.chuys.gshp.pdv.data.repository

import android.util.Log
import com.chuys.gshp.pdv.domain.model.PdvData
import com.chuys.gshp.pdv.domain.repository.PdvRepository
import io.reactivex.Single


class PdvDataRepository : PdvRepository {

    override fun getAllPdv(): Single<List<PdvData>> {
        //This method must change by realtime
        val pdvData = PdvData(
            1, "", 1,
            "", "", "", 1,
            "", "", "",
            "", "", "", 0.0, 0.0
        )
        Log.e("repo","repooo")
        return Single.just(listOf(pdvData))
    }
}

