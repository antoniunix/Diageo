package com.chuys.gshp.pdv.domain.repository

import com.chuys.gshp.pdv.domain.model.ReportReportModel
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

interface ReportRepository {
    fun saveReport(report: ReportReportModel?):Single<Resource<Boolean>>
    fun updateReport(report: ReportReportModel?):Single<Resource<Boolean>>
    fun getReport():Single<Resource<ReportReportModel>>
}