package com.chuys.gshp.pdv.data.repository

import com.chuys.gshp.pdv.domain.model.CheckModel
import com.chuys.gshp.pdv.domain.repository.CheckRepository
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

class CheckDataRepository : CheckRepository {

    override fun saveCheck(report: CheckModel?): Single<Resource<Boolean>> {
        return Single.create {
            if (report != null) {
                ReportCheckEntity().writeCheck(report)
                it.onSuccess(
                    Resource.success(true, "")
                )
            }
        }
    }
}