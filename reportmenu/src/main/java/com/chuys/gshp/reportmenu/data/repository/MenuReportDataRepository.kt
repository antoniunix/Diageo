package com.chuys.gshp.reportmenu.data.repository

import android.util.Log
import com.chuys.gshp.reportmenu.data.mapper.ModuleModelMapper
import com.chuys.gshp.reportmenu.data.model.Module
import com.chuys.gshp.reportmenu.domain.model.Modules
import com.chuys.gshp.reportmenu.domain.repository.MenuReportRepository
import com.chuys.gshp.shared.data.database.RealmTimeDbConfig
import com.chuys.gshp.shared.domain.constant.StringConstant
import com.chuys.gshp.shared.domain.models.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Single


class MenuReportDataRepository : MenuReportRepository {
    lateinit var referenceDb: DatabaseReference
    val mapper = ModuleModelMapper()
    override fun getReferenceToDb() {
        referenceDb = RealmTimeDbConfig.getReference("data").child("module")
    }


    override fun getModules(): Single<Resource<ArrayList<Modules>>> {
        getReferenceToDb()
        return getInfoFromRealDataBase()
    }

    private fun getInfoFromRealDataBase(): Single<Resource<ArrayList<Modules>>> {
        val moduleList = ArrayList<Module>()
        return Single.create {
            referenceDb.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        val module = snapshot.getValue(Module::class.java)
                        moduleList.add(module!!)
                        Log.e("MODEL","MODEL "+module.toString())
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