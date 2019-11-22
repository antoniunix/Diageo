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
                "id INTEGER," +
                "idReport INTEGER," +
                "uuid TEXT," +
                "idSku TEXT," +
                "answer INTEGER," +
                "lastSync INTEGER)"
    }

    override fun getPriceReport(): String {
        return "CREATE TABLE price_report(" +
                "id INTEGER," +
                "idReport INTEGER," +
                "uuid TEXT," +
                "idSku TEXT," +
                "answer TEXT," +
                "lastSync INTEGER)"
    }

}