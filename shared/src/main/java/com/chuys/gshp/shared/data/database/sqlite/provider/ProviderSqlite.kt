package com.chuys.gshp.shared.data.database.sqlite.provider

import android.content.Context

class ProviderSqlite(context: Context) : ProviderSqliteResources {


    private var context: Context

    init {
        this.context = context
    }

    override fun getContext(): Context {
        return context
    }

    override fun getDatabaseName(): String {
        return "Diageo.db"
    }

    override fun getVersionDataBase(): Int {
        return 1
    }

    override fun getItemRealmTable(): String {
        return "CREATE TABLE item(" +
                "id INTEGER," +
                "itemTypeId INTEGER," +
                "description TEXT," +
                "name TEXT," +
                "lastSync INTEGER)"
    }

    override fun getItemPropsRealmTable(): String {
        return "CREATE TABLE itemProps(" +
                "id INTEGER," +
                "availabilityPrice TEXT," +
                "Categoria TEXT," +
                "Marca TEXT," +
                "maxPrice TEXT," +
                "minPrice TEXT," +
                "Subcategoria TEXT," +
                "lastSync INTEGER)"
    }

    override fun getAvailabilityReport(): String {
        return "CREATE TABLE availability_report(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idReport INTEGER," +
                "uuid TEXT," +
                "idSku TEXT," +
                "answer INTEGER," +
                "lastSync INTEGER)"
    }

    override fun getPriceReport(): String {
        return "CREATE TABLE price_report(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idReport INTEGER," +
                "uuid TEXT," +
                "idSku TEXT," +
                "answer TEXT," +
                "lastSync INTEGER)"
    }

    override fun getReport():String{
        return "CREATE TABLE report_report("+
                "idReport INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idPdv INTEGER,"+
                "date_checkin INTEGER,"+
                "date_checkout INTEGER)"
    }

    override fun getReportCheck(): String {
       return "CREATE TABLE report_check("+
               "id INTEGER PRIMARY KEY AUTOINCREMENT," +
               "idReport INTEGER," +
               "lat REAL,"+
               "lon REAL,"+
               "date INTEGER,"+
               "type INTEGER)"
    }

    override fun getSiteInterest(): String {
       return "CREATE TABLE siteInterest("+
               "idPdv INTEGER, "+
               "code TEXT,"+
               "lat REAL,"+
               "lon REAL,"+
               "name TEXT,"+
               "address TEXT,"+
               "lastSync INTEGER)"
    }



}