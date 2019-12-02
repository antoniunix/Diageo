package com.chuys.gshp.pdv.data.repository

import android.util.Log
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
                insStmnt.bindLong(1, value.idPdv)
            } catch (e: java.lang.Exception) {
                insStmnt.bindNull(1)
            }
            try {
                insStmnt.bindLong(2, value.date_checkin)
            } catch (e: java.lang.Exception) {
                insStmnt.bindNull(2)
            }
            try {
                insStmnt.bindLong(3, value.date_checkout)
            } catch (e: java.lang.Exception) {
                insStmnt.bindNull(3)
            }
            insStmnt.execute()
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.endTransaction()
        }
    }

    fun getRepoert(): ReportReportModel {
        var db = sqliteHelper.getReadableDatabase()
        var qry = "SELECT * FROM \n" +
                tableName + "\nWHERE date_checkout=0"
        val cursor = db.rawQuery(qry, null)
        var catalogo: ReportReportModel = ReportReportModel(0, 0, 0, 0)
        if (cursor.moveToFirst()) {
            val idReport = cursor.getColumnIndexOrThrow("idReport")
            val idPdv = cursor.getColumnIndexOrThrow("idPdv")
            val dateCheckIn = cursor.getColumnIndexOrThrow("date_checkin")

            catalogo = ReportReportModel(
                cursor.getString(idReport).toLong(),
                cursor.getString(idPdv).toLong(),
                cursor.getString(dateCheckIn).toLong(),
                0
            )
        }
        cursor.close()
        db.close()
        return catalogo
    }

    fun updateReportReport(values: ReportReportModel) {
            val db = sqliteHelper.getReadableDatabase()
            val qry = "UPDATE  \n" +
                    tableName + "\n set date_checkout="+values.date_checkout+
                    "\n WHERE idReport="+values.idReport

            val cursor = db.rawQuery(qry, null)
            if(cursor.moveToFirst()){
                Log.e("save","save check")
            }
            cursor.close()
            db.close()

    }


}