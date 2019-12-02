package com.chuys.gshp.pdv.data.repository

import com.chuys.gshp.pdv.domain.model.ReportReportModel
import com.chuys.gshp.pdv.domain.repository.ReportRepository
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

class ReportDataRepository : ReportRepository {


    override fun saveReport(report: ReportReportModel?): Single<Resource<Boolean>> {
        return Single.create {
            if (report != null) {
                ReportReportEntity().writeReportReport(report)
                it.onSuccess(
                    Resource.success(true, "")
                )
            }
        }
    }
}