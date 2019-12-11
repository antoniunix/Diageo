package com.chuys.gshp.visits.view.data.repository

import com.chuys.gshp.shared.data.database.realtime.RealmTimeDbConfig
import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.visits.view.domain.model.VisitModel
import com.chuys.gshp.visits.view.domain.repository.VisitsRepository
import io.reactivex.Single

class VisitsDataRepository : VisitsRepository {


    override fun getPdvVisited(): Single<Resource<List<VisitModel>>> {

        return Single.just(VisitsEntity().getItem()).map { result ->
            when {
                !result.isEmpty() -> {
                    return@map Resource.success(result, StringConstant.EMPTY_STRING)
                }
                else -> Resource.error<List<VisitModel>>(StringConstant.DONT_HAVE_DATA_IN_DATABASE_ESP)

            }
        }
    }

}
