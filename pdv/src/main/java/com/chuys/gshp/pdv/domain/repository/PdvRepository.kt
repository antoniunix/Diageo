package com.chuys.gshp.pdv.domain.repository

import com.chuys.gshp.pdv.domain.model.PdvModel
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

interface PdvRepository {
    fun getReferenceToDb()
    fun getAllPdv(): Single<Resource<List<PdvModel>>>
}