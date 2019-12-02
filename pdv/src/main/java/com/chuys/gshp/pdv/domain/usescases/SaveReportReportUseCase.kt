package com.chuys.gshp.pdv.domain.usescases

import com.chuys.gshp.pdv.domain.model.ReportReportModel
import com.chuys.gshp.pdv.domain.repository.ReportRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import io.reactivex.Single

class SaveReportReportUseCase constructor(
    private val reportRepository: ReportRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
):SingleUseCase<ReportReportModel,Resource<Boolean>>(threadExecutor,postExecutionThread){
    override fun buildUseCase(params: ReportReportModel?): Single<Resource<Boolean>> {
        return reportRepository.saveReport(params)
    }

}