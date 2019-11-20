package com.chuys.gshp.shared.data.database.realtime

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RealmTimeDbConfig {

    companion object : RealmTimeConfig {

        override fun initDb(): FirebaseDatabase {
            return FirebaseDatabase.getInstance()
        }

        override fun getReference(nameNodo: String): DatabaseReference {
            return initDb()
                .getReference(nameNodo)
        }
    }


}