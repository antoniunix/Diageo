package com.chuys.gshp.pdv.domain.repository

import com.chuys.gshp.pdv.domain.model.PdvData
import io.reactivex.Single

interface PdvRepository {
    fun getAllPdv(): Single<List<PdvData>>
}