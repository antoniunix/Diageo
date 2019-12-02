package com.chuys.gshp.pdv.data.repository

import com.chuys.gshp.pdv.domain.model.ReportReportModel
import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider

class ReportReportEntity {

    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "report_report"

    fun writeReportReport(value: ReportReportModel) {
        val db = sqliteHelper.writableDatabase

        try {
            var insStmnt = db.compileStatement(
                "" +
                        "INSERT INTO "
                        + tableName + "(idPdv,date_checkin,date_checkout) "
                        + "VALUES (?,?,?);"
            )
            db.beginTransaction()

            try {
                insStmnt.bindLong(1,value.idPdv)
            }catch (e:java.lang.Exception){
                insStmnt.bindNull(1)
            }
            try {
                insStmnt.bindLong(2,value.date_checkin)
            }catch (e:java.lang.Exception){
                insStmnt.bindNull(2)
            }
            try {
                insStmnt.bindLong(3,value.date_checkout)
            }catch (e:java.lang.Exception){
                insStmnt.bindNull(3)
            }
            insStmnt.execute()
            db.setTransactionSuccessful()
        }catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.endTransaction()
        }
    }

}