package com.chuys.gshp.shared.data.database

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

interface RealmTimeConfig {

    fun initDb(): FirebaseDatabase
    fun getReference(nameNodo: String): DatabaseReference
}