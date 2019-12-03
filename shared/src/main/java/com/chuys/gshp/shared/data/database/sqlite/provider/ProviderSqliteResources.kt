package com.chuys.gshp.shared.data.database.sqlite.provider

import android.content.Context

interface ProviderSqliteResources {
    fun getContext(): Context
    fun getDatabaseName(): String
    fun getVersionDataBase(): Int
    fun getItemRealmTable():String
    fun getItemPropsRealmTable():String
    fun getAvailabilityReport():String
    fun getPriceReport():String
    fun getReportCheck():String
    fun getReport():String
    fun getSiteInterest():String

}