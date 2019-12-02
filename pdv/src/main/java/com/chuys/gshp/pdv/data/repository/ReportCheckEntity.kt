package com.chuys.gshp.pdv.data.repository

import com.chuys.gshp.pdv.domain.model.CheckModel
import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider

class ReportCheckEntity {

    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "report_check"

    fun writeItem(value: CheckModel) {
        var db = sqliteHelper.writableDatabase

        try {
            var insStmnt = db.compileStatement(
                "" + "INSERT INTO "
                        + tableName + " (idReport,lat,lon,date) "
                        + "VALUES (?,?,?,?);"
            )
            db.beginTransaction()
            try {
                insStmnt.bindLong(1, value.idReport)
            } catch (e: Exception) {
                insStmnt.bindNull(1)
            }
            try {
                insStmnt.bindDouble(2, value.lat)
            } catch (e: java.lang.Exception) {
                insStmnt.bindNull(2)
            }
            try {
                insStmnt.bindDouble(3, value.lon)
            } catch (e: java.lang.Exception) {
                insStmnt.bindNull(3)
            }
            try {
                insStmnt.bindLong(4, value.date)
            } catch (e: java.lang.Exception) {
                insStmnt.bindNull(4)
            }
            insStmnt.execute()
            db.setTransactionSuccessful()

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.endTransaction()
        }
    }

}