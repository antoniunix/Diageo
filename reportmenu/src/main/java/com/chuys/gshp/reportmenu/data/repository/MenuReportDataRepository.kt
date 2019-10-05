package com.chuys.gshp.reportmenu.data.repository

import com.chuys.gshp.reportmenu.domain.model.Modules
import com.chuys.gshp.reportmenu.domain.repository.MenuReportRepository
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

class MenuReportDataRepository : MenuReportRepository {
    override fun getModules(): Single<Resource<List<Modules>>> {
        //This method must change by realtime
        val modules = Modules(
            1, "Disp and Price", 1, 1, false, "", ""
        )
        val modules2 = Modules(
            2, "Poll", 1, 2, false, "", ""
        )
        return Single.just(listOf(modules, modules2)).map { result ->
            when {
                !result.isEmpty() -> {
                    return@map Resource.success(result, StringConstant.EMPTY_STRING)
                }
                else -> Resource.error<List<Modules>>(StringConstant.DONT_HAVE_DATA_IN_DATABASE_ESP)
            }
        }
    }
}