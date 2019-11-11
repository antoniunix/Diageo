package com.chuys.gshp.reportmenu.domain.usescase

import com.chuys.gshp.reportmenu.domain.model.Modules
import com.chuys.gshp.reportmenu.domain.repository.MenuReportRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import io.reactivex.Single

class ReportMenuUseCase constructor(
    private val menuReportRepository: MenuReportRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Any, Resource<ArrayList<Modules>>>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: Any?): Single<Resource<ArrayList<Modules>>> {
        return menuReportRepository.getModules()
    }
}