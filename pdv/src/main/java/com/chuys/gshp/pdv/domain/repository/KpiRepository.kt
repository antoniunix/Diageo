package com.chuys.gshp.pdv.domain.repository

import com.chuys.gshp.pdv.data.model.KpiData
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

interface KpiRepository {
    fun getReferenceToDb(idSite: String?)

    fun getData(idSite: String?): Single<Resource<KpiData>>
}