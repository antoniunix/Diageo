package com.chuys.gshp.reportmenu.data.provider

import com.chuys.gshp.reportmenu.data.repository.MenuReportDataRepository
import com.chuys.gshp.reportmenu.domain.model.Modules
import com.chuys.gshp.reportmenu.domain.providers.ReportMenuProvider
import com.chuys.gshp.reportmenu.domain.usescase.ReportMenuUseCase
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

class ReportMenuDataProvider(
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread
) : ReportMenuProvider {

    private val menuReporaRepository = MenuReportDataRepository()

    override fun getModulerUseCase(): SingleUseCase<Any, Resource<List<Modules>>> {
        return ReportMenuUseCase(menuReporaRepository, jobExecutor, uiThread)
    }

}