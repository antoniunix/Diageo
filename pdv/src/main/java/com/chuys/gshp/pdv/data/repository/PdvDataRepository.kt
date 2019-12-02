package com.chuys.gshp.pdv.data.repository

import com.chuys.gshp.pdv.data.mapper.PdvMapper
import com.chuys.gshp.pdv.data.model.PdvData
import com.chuys.gshp.pdv.domain.model.PdvModel
import com.chuys.gshp.pdv.domain.repository.PdvRepository
import com.chuys.gshp.shared.data.database.realtime.RealmTimeDbConfig
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Single
import java.lang.Exception


class   PdvDataRepository : PdvRepository {

    lateinit var referenceDb: DatabaseReference
    val mapper = PdvMapper()
    override fun getReferenceToDb() {
        referenceDb = RealmTimeDbConfig.getReference("data").child("siteInterest")
    }


    override fun getAllPdv(): Single<Resource<List<PdvModel>>> {
        getReferenceToDb()
        return getInfoFromRealDataBase()
    }

    private fun getInfoFromRealDataBase(): Single<Resource<List<PdvModel>>> {
        val moduleList = ArrayList<PdvData>()
        return Single.create {
            referenceDb.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var pdv: PdvData?
                    for (snapshot in dataSnapshot.children) {
                        try {
                            pdv = snapshot.getValue(PdvData::class.java)
                        } catch (e: Exception) {
                            continue
                        }
                        moduleList.add(pdv!!)
                    }
                    it.onSuccess(
                        Resource.success(
                            mapper.transform(moduleList),
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

