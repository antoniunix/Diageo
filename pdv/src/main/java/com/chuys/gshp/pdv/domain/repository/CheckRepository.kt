package com.chuys.gshp.pdv.domain.repository

import com.chuys.gshp.pdv.domain.model.CheckModel
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

interface CheckRepository {

    fun saveCheck(report : CheckModel?): Single<Resource<Boolean>>
}