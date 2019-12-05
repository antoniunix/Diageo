package com.chuys.gshp.shared.data.database.sqlite

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.chuys.gshp.shared.data.database.sqlite.provider.ProviderSqliteResources

class SqliteInstance(providerSqliteResources: ProviderSqliteResources) : SQLiteOpenHelper(
    providerSqliteResources.getContext(),
    providerSqliteResources.getDatabaseName(),
    null, providerSqliteResources.getVersionDataBase()
) {

    private val providerSqliteResources: ProviderSqliteResources

    init {
        this.providerSqliteResources = providerSqliteResources
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(providerSqliteResources.getItemRealmTable())
        db?.execSQL(providerSqliteResources.getItemPropsRealmTable())
        db?.execSQL(providerSqliteResources.getAvailabilityReport())
        db?.execSQL(providerSqliteResources.getPriceReport())
        db?.execSQL(providerSqliteResources.getReportCheck())
        db?.execSQL(providerSqliteResources.getReport())


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //to future
    }



}