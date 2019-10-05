package com.chuys.gshp.pdv.domain.providers

import com.chuys.gshp.pdv.domain.model.PdvData
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface PdvProvider {
    fun getListPdvUseCase(): SingleUseCase<Any, Resource<List<PdvData>>>
}