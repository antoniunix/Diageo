package com.chuys.gshp.pdv.data.repository

import com.chuys.gshp.pdv.data.model.KpiData
import com.chuys.gshp.pdv.domain.repository.KpiRepository
import com.chuys.gshp.shared.data.database.realtime.RealmTimeDbConfig
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Single

class KpiDataRepository : KpiRepository {

    lateinit var referenceDb: DatabaseReference

    override fun getReferenceToDb(idSite: String?) {
        referenceDb =
            RealmTimeDbConfig.getReference("kpi").child("services").child("siteInterest").child(
                idSite ?: ""
            )

    }

    override fun getData(idSite: String?): Single<Resource<KpiData>> {
        getReferenceToDb(idSite)
        return getSite()

    }

    private fun getSite(): Single<Resource<KpiData>> {
        return Single.create {
            var kpiData: KpiData? = null
            referenceDb.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    try {
                        kpiData = dataSnapshot.getValue(KpiData::class.java)
                    } catch (e: Exception) {

                    }
                    it.onSuccess(
                        Resource.success(
                            kpiData,
                            StringConstant.EMPTY_STRING
                        )
                    )
                }

                override fun onCancelled(p0: DatabaseError) {
                    it.onError(Throwable("Error"))
                }
            })
        }

    }


}