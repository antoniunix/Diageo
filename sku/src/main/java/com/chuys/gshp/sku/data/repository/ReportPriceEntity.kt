package com.chuys.gshp.sku.data.repository

import android.util.Log
import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider
import com.chuys.gshp.sku.data.model.ReportPriceModel
import java.util.*

class ReportPriceEntity {
    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "price_report"

    fun writeItem(valueList: List<ReportPriceModel>) {
        var db = sqliteHelper.writableDatabase
        try {
            var insStmnt = db.compileStatement(
                "" + "INSERT INTO "
                        + tableName + " (idReport,uuid,idSku,answer,lastSync) "
                        + "VALUES(?,?,?,?,?);"
            )
            db.beginTransaction()
            for (value in valueList) {
                try {
                    insStmnt.bindLong(1, value.idReport)
                } catch (e: Exception) {
                    insStmnt.bindNull(1)
                }
                try {
                    insStmnt.bindString(2, UUID.randomUUID().toString())
                } catch (e: Exception) {
                    insStmnt.bindNull(2)
                }
                try {
                    insStmnt.bindString(3, value.idSku)
                } catch (e: Exception) {
                    insStmnt.bindNull(3)
                }
                try {
                    insStmnt.bindString(4, value.answer)
                } catch (e: Exception) {
                    insStmnt.bindNull(4)
                }
                try {
                    insStmnt.bindString(5, value.lastSync)
                } catch (e: Exception) {
                    insStmnt.bindNull(5)
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

    fun deleteByIdReport(idReport: Long) {
        sqliteHelper.readableDatabase.execSQL("delete from $tableName WHERE idReport=$idReport")
    }
}