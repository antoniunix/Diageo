package com.chuys.gshp.visits.view.domain.provider

import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import com.chuys.gshp.visits.view.domain.model.VisitModel
import io.reactivex.Single

interface VisitProvider {
    fun getVisitUseCase(): SingleUseCase<Any, Resource<List<VisitModel>>>
}