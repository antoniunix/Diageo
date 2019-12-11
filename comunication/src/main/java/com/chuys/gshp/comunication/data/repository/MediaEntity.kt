package com.chuys.gshp.comunication.data.repository

import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider

class MediaEntity {

    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "media"

    fun writeMedia(valueList: List<MediaModel>){
        var db = sqliteHelper.writableDatabase
        try {
            var insStmnt = db.compileStatement(
                ""+ "INSERT INTO "
            +tableName+ " (title,url,description,mediaType) VALUES (?,?,?,?);"
            )
            db.beginTransaction()
            for (value in valueList){
                try {
                    insStmnt.bindString(1,value.title)
                }catch (e:Exception){
                    insStmnt.bindNull(1)
                }
                try {
                    insStmnt.bindString(2,value.url)
                }catch (e:Exception){
                    insStmnt.bindNull(2)
                }
                try {
                    insStmnt.bindString(3, value.description)
                }catch (e:Exception){
                    insStmnt.bindNull(3)
                }
                try {
                    insStmnt.bindLong(4, value.mediaType.toLong())
                }catch (e:java.lang.Exception){
                    insStmnt.bindNull(4)
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

}