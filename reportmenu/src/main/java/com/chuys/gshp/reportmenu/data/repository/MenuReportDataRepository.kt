package com.chuys.gshp.reportmenu.data.repository

import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.reportmenu.domain.model.Modules
import com.chuys.gshp.reportmenu.domain.repository.MenuReportRepository
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

class MenuReportDataRepository : MenuReportRepository {
    override fun getModules(): Single<Resource<List<Modules>>> {
        //This method must change by realtime
        val modules = Modules(
            1, "Disp and Price", 1, 1, false, "", "", Activities.PRICE_AND_AVAILABILITY
        )
        val modules2 = Modules(
            2, "Poll", 1, 2, false, "", "", Activities.POLL
        )
        val modules3 = Modules(
            3, "Ejecutables", 1, 3, false, "", "", Activities.EXECUTABLE
        )
        val modules4 = Modules(
            4, "Levantamiento de Venta", 1, 4, false, "", "", Activities.ORDER
        )
        return Single.just(listOf(modules, modules2, modules3, modules4)).map { result ->
            when {
                !result.isEmpty() -> {
                    return@map Resource.success(result, StringConstant.EMPTY_STRING)
                }
                else -> Resource.error<List<Modules>>(StringConstant.DONT_HAVE_DATA_IN_DATABASE_ESP)
            }
        }
    }
}