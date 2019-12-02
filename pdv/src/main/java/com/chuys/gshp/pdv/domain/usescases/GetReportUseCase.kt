package com.chuys.gshp.pdv.domain.usescases

import com.chuys.gshp.pdv.domain.model.ReportReportModel
import com.chuys.gshp.pdv.domain.repository.ReportRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import io.reactivex.Single

class GetReportUseCase constructor(private val reportRepository: ReportRepository,
                                   threadExecutor: ThreadExecutor,
                                   postExecutionThread: PostExecutionThread
):SingleUseCase<Any, Resource<ReportReportModel>>(threadExecutor,postExecutionThread){
    override fun buildUseCase(params: Any?): Single<Resource<ReportReportModel>> {
        return reportRepository.getReport()
    }


}