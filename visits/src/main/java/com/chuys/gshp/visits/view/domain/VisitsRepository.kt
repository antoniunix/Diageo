package com.chuys.gshp.visits.view.domain.repository

import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.visits.view.domain.model.VisitModel
import io.reactivex.Single

interface VisitsRepository {
    fun getPdvVisited(): Single<Resource<List<VisitModel>>>
}
