package com.chuys.gshp.shared.data.realdb

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.logging.Logger

class MainDB {
    companion object {
        lateinit var database: FirebaseDatabase
        fun initRealDB() {
            database = FirebaseDatabase.getInstance()
            val ref = database.getReference("message")
            ref.setValue("Hola!!!")
            Logger.getLogger(MainDB::class.java.name).warning("RealBD - Enviado: ${ref.key}")


            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val value = p0.getValue(String::class.java)
                    Logger.getLogger(MainDB::class.java.name).warning("RealBD - change values: $value")
                }

                override fun onCancelled(p0: DatabaseError) {
                    Logger.getLogger(MainDB::class.java.name)
                        .warning("RealBD - failed ${p0.toException()}")
                }

            })
        }
    }
}