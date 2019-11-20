package com.chuys.gshp.diageo

import android.app.Application
import com.chuys.gshp.shared.data.database.sqlite.SqliteInstance
import com.chuys.gshp.shared.data.database.sqlite.provider.ProviderSqlite
import com.chuys.gshp.shared.data.database.sqlite.provider.ProviderSqliteResources
import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider
import com.facebook.stetho.Stetho

class DiageoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val sqliteInstance: ProviderSqliteResources = ProviderSqlite(this)
        Stetho.initializeWithDefaults(this);
        SqliteDBProvider.setDbHelper(SqliteInstance(sqliteInstance))
    }
}