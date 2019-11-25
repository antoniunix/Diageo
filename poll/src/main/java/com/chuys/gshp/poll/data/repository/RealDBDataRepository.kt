package com.chuys.gshp.poll.data.repository

import android.util.Log
import com.chuys.gshp.poll.domain.model.Form
import com.chuys.gshp.poll.domain.repository.RealDBRepository
import com.chuys.gshp.shared.data.database.realtime.RealmTimeDbConfig
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Single
import java.util.logging.Logger

class RealDBDataRepository : RealDBRepository {

    lateinit var referenceDb: DatabaseReference

    override fun getData(): Single<List<Form>> {
        getReference()
        return initRealDBData()
    }

    override fun getReference() {
        referenceDb = RealmTimeDbConfig.getReference("data").child("form")
    }

    private fun initRealDBData(): Single<List<Form>> {
        val forms = ArrayList<Form>()
        return Single.create { single ->
            Logger.getLogger(RealDBDataRepository::class.java.name)
                .warning("RealBD - Enviado: ${referenceDb.key}")

            referenceDb.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    var form: Form?
                    for (item in p0.children) {
                        try {
                            form = item.getValue(Form::class.java)
                            forms.add(form!!)
                        } catch (e: Exception) {
                            val sync = item.getValue(Int::class.java)
                            Log.wtf("sync", sync.toString())
                            continue
                        }
                    }
                    single.onSuccess(forms)
                }

                override fun onCancelled(p0: DatabaseError) {
                    Logger.getLogger(RealDBDataRepository::class.java.name)
                        .warning("RealBD - failed ${p0.toException()}")
                    single.onError(Exception(p0.details))
                }

            })
        }
    }
}