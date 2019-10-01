package com.chuys.gshp.pdv.domain.repository

import com.chuys.gshp.pdv.domain.model.PdvData
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

interface PdvRepository {
    fun getAllPdv(): Single<Resource<List<PdvData>>>
}