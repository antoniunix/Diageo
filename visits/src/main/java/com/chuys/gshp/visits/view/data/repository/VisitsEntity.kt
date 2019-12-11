package com.chuys.gshp.visits.view.data.repository

import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider
import com.chuys.gshp.visits.view.domain.model.VisitModel

class VisitsEntity {
    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "item"

    fun getItem(): List<VisitModel> {
        var db = sqliteHelper.getReadableDatabase()
        val qry = "SELECT DISTINCT\n" +
                "report_report.idReport,\n" +
                "report_report.idPdv,\n" +
                "report_report.date_checkin,\n" +
                "report_report.date_checkout\n" +
                "FROM\n" +
                "report_report"
        var cursor = db.rawQuery(qry, null)
        val obj = ArrayList<VisitModel>()
        var catalogo: VisitModel
        if (cursor.moveToFirst()) {
            val id = cursor.getColumnIndexOrThrow("idReport")
            val idPdv = cursor.getColumnIndexOrThrow("idPdv")
            val dateCheckin = cursor.getColumnIndexOrThrow("date_checkin")
            val dateCheckout = cursor.getColumnIndexOrThrow("date_checkout")
            do {
                catalogo = VisitModel(
                    cursor.getLong(id),
                    cursor.getLong(idPdv),
                    cursor.getLong(dateCheckin),
                    cursor.getLong(dateCheckout),
                    1,"wertyuikjhgfd",0,"PDV TEST"
                )
                obj.add(catalogo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return obj
    }

}
