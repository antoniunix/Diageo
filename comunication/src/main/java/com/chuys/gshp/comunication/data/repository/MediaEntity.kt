package com.chuys.gshp.comunication.data.repository

import com.chuys.gshp.comunication.data.model.MediaData
import com.chuys.gshp.comunication.domain.model.ComunicationModel
import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider

class MediaEntity {

    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "media"

    fun writeMedia(valueList: List<MediaData>) {
        val db = sqliteHelper.writableDatabase
        try {
            var insStmnt = db.compileStatement(
                "" + "INSERT INTO "
                        + tableName + " (title,url,description,mediaType) VALUES (?,?,?,?);"
            )
            db.beginTransaction()
            for (value in valueList) {
                try {
                    insStmnt.bindString(1, value.title)
                } catch (e: Exception) {
                    insStmnt.bindNull(1)
                }
                try {
                    insStmnt.bindString(2, value.url)
                } catch (e: Exception) {
                    insStmnt.bindNull(2)
                }
                try {
                    insStmnt.bindString(3, value.description)
                } catch (e: Exception) {
                    insStmnt.bindNull(3)
                }
                try {
                    insStmnt.bindLong(4, value.mediaType.toLong())
                } catch (e: java.lang.Exception) {
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

    fun getItem(): List<ComunicationModel> {
        val db = sqliteHelper.readableDatabase
        val qry = "SELECT DISTINCT\n" +
                "media.id,"
                "media.title,\n" +
                "media.url,\n" +
                "media.description,\n" +
                "media_type.ext\n" +
                "media_type.ext\n" +
                "media_type.name\n" +
                "from media\n" +
                "inner join media_type ON media.mediaType=media_type.id"
        val cursor = db.rawQuery(qry, null)
        val obj = listOf<ComunicationModel>()
        var catalogo: ComunicationModel
        if(cursor.moveToFirst()){
            val title= cursor.getColumnIndexOrThrow("title")
            val url=cursor.getColumnIndexOrThrow("url")
            val description=cursor.getColumnIndexOrThrow("description")
            val nameFile=cursor.getColumnIndexOrThrow("id")
            val ext= cursor.getColumnIndexOrThrow("ext")
            do {
                catalogo=

            }while (cursor.moveToNext())
        }
    }

    fun deleteAll() {
        val db = sqliteHelper.getReadableDatabase()
        db.execSQL("delete from $tableName")
    }
}