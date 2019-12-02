package com.chuys.gshp.pdv.data.provider

import com.chuys.gshp.pdv.data.repository.ReportDataRepository
import com.chuys.gshp.pdv.domain.model.ReportReportModel
import com.chuys.gshp.pdv.domain.providers.ReportProvider
import com.chuys.gshp.pdv.domain.usescases.GetReportUseCase
import com.chuys.gshp.pdv.domain.usescases.SaveReportReportUseCase
import com.chuys.gshp.pdv.domain.usescases.UpdateReportUseCase
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

class ReportDataProvider (
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread
) :ReportProvider {
    private val repository=ReportDataRepository()
    override fun saveReportReport(): SingleUseCase<ReportReportModel, Resource<Boolean>> {
        return SaveReportReportUseCase(repository,jobExecutor,uiThread)
    }

    override fun getReport(): SingleUseCase<Any, Resource<ReportReportModel>> {
       return GetReportUseCase(repository,jobExecutor,uiThread)
    }

    override fun updateReport(): SingleUseCase<ReportReportModel, Resource<Boolean>> {
      return UpdateReportUseCase(repository,jobExecutor,uiThread)
    }
}