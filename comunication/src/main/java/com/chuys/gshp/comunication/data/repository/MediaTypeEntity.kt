package com.chuys.gshp.comunication.data.repository

import com.chuys.gshp.comunication.data.model.MediaTypeData
import com.chuys.gshp.comunication.domain.model.MediaTypeModel
import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider

class MediaTypeEntity {

    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "media_type"

    fun writeItem(valueList: List<MediaTypeData>) {
        val db = sqliteHelper.writableDatabase
        try {
            val insStmnt = db.compileStatement(
                "" + "INSERT INTO "
                        + tableName + " (id,ext,name) "
                        + "VALUES(?,?,?);"
            )
            db.beginTransaction()
            for (value in valueList) {
                try {
                    insStmnt.bindLong(1, value.id!!.toLong())
                } catch (e: java.lang.Exception) {
                    insStmnt.bindNull(1)
                }
                try {
                    insStmnt.bindString(2, value.ext)
                } catch (e: java.lang.Exception) {
                    insStmnt.bindNull(2)
                }
                try {
                    insStmnt.bindString(3, value.name)
                } catch (e: java.lang.Exception) {
                    insStmnt.bindNull(3)
                }
                insStmnt.executeInsert()
            }
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.endTransaction()
        }
    }

    fun deleteAll() {
        var db = sqliteHelper.getReadableDatabase()
        db.execSQL("delete from " + tableName)
    }
}