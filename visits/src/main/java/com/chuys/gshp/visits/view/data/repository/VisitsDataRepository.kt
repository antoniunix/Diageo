package com.chuys.gshp.visits.view.data.repository

import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.visits.view.domain.model.VisitModel
import com.chuys.gshp.visits.view.domain.repository.VisitsRepository
import io.reactivex.Single

class VisitsDataRepository : VisitsRepository {

    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "item"

    override fun getPdvVisited(): Single<Resource<List<VisitModel>>> {
        val visist = VisitModel(
            1, 1, 1234567892, 9876543211,
            1, "sdfghjk", 0, "pdv"
        )
        val visist1 = VisitModel(
            1, 1, 1234567893, 9876543212,
            1, "sdfghjk", 0, "pdv1"
        )
        return Single.just(listOf(visist, visist1)).map { result ->
            when {
                !result.isEmpty() -> {
                    return@map Resource.success(result, StringConstant.EMPTY_STRING)
                }
                else -> Resource.error<List<VisitModel>>(StringConstant.DONT_HAVE_DATA_IN_DATABASE_ESP)

            }
        }
    }

}
