package com.chuys.gshp.pdv.domain.providers

import com.chuys.gshp.pdv.data.model.KpiData
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface KpiProvider {
    fun getData():SingleUseCase<String, Resource<KpiData>>
}