package com.chuys.gshp.sku.data.repository

import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider
import com.chuys.gshp.sku.data.model.SiteInterestModel

class SiteInterestEntity {

    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "siteInterest"

    fun writeSiteInterest(valueList: List<SiteInterestModel>){
        var db = sqliteHelper.writableDatabase

        try {
            val insStmnt= db.compileStatement(
                ""+"INSERT INTO "+
                        tableName+ " (address,code,lat,lon,name) "+
                        "VALUES (?,?,?,?,?);"
            )
            db.beginTransaction()
            for (value in valueList){
                try {
                    insStmnt.bindString(1,value.address)
                }catch (e: java.lang.Exception){
                    insStmnt.bindNull(1)
                }
                try {
                    insStmnt.bindString(2,value.code)
                }catch (e: java.lang.Exception){
                    insStmnt.bindNull(2)
                }
                try {
                    insStmnt.bindDouble(3,value.lat)
                }catch (e:java.lang.Exception){
                    insStmnt.bindNull(3)
                }
                try {
                    insStmnt.bindDouble(4,value.lon)
                }catch (e: java.lang.Exception){
                    insStmnt.bindNull(4)
                }
                try {
                    insStmnt.bindString(5,value.name)
                }catch (e:java.lang.Exception){
                    insStmnt.bindNull(5)
                }
                insStmnt.executeInsert()
            }
            db.beginTransaction()
        }catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.endTransaction()
        }
    }

}