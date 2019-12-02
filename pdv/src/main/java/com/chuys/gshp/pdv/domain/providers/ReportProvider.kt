package com.chuys.gshp.pdv.domain.providers

import com.chuys.gshp.pdv.domain.model.ReportReportModel
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface ReportProvider {
    fun saveReportReport():SingleUseCase<ReportReportModel,Resource<Boolean>>
}