package com.chuys.gshp.pdv.domain.providers

import com.chuys.gshp.pdv.domain.model.CheckModel
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface CheckProvider {
    fun saveReportCheck():SingleUseCase<CheckModel, Resource<Boolean>>
}