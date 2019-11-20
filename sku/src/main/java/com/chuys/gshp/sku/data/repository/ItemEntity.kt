package com.chuys.gshp.sku.data.repository

import android.util.Log
import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider
import com.chuys.gshp.sku.data.model.ItemModel
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData

class ItemEntity {

    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "item"

    fun writeItem(valueList: List<ItemModel>) {
        var db = sqliteHelper.writableDatabase
        try {

            var insStmnt = db.compileStatement(
                "" + "INSERT INTO "
                        + tableName + " (id,itemTypeId,description,name) "
                        + "VALUES(?,?,?,?);"
            )
            db.beginTransaction()
            for (value in valueList) {
                try {
                    insStmnt.bindLong(1, value.id)
                } catch (e: Exception) {
                    insStmnt.bindNull(1)
                }
                try {
                    insStmnt.bindLong(2, value.itemTypeId)
                } catch (e: Exception) {
                    insStmnt.bindNull(2)
                }
                try {
                    insStmnt.bindString(3, value.description)
                } catch (e: Exception) {
                    insStmnt.bindNull(3)
                }
                try {
                    insStmnt.bindString(4, value.name)
                } catch (e: Exception) {
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

    fun getItem(): List<SkuAvailabilityAndPriceData> {
        var db = sqliteHelper.getReadableDatabase()
        val qry = "SELECT\n" +
                "item.id,\n" +
                "item.itemTypeId,\n" +
                "item.description,\n" +
                "item.name\n" +
                "FROM\n" +
                "item"
        var cursor = db.rawQuery(qry, null)
        val obj = ArrayList<SkuAvailabilityAndPriceData>()
        Log.e("Cursor", "cursor " + cursor.count)
        var catalogo: SkuAvailabilityAndPriceData
        if (cursor.moveToFirst()) {
            val id = cursor.getColumnIndexOrThrow("id")
            val itemTypeId = cursor.getColumnIndexOrThrow("itemTypeId")
            val description = cursor.getColumnIndexOrThrow("description")
            val name = cursor.getColumnIndexOrThrow("name")
            do {
                catalogo = SkuAvailabilityAndPriceData(
                    cursor.getString(id),
                    cursor.getInt(itemTypeId),
                    cursor.getString(name),
                    "", 1, 1, "1", false
                )
                obj.add(catalogo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return obj
    }

    fun deleteAll(){
        var db = sqliteHelper.getReadableDatabase()
        db.execSQL("delete from "+ tableName)
    }

}