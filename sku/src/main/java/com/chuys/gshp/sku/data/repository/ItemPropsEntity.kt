package com.chuys.gshp.sku.data.repository

import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider
import com.chuys.gshp.sku.data.model.ItemPropsModel

class ItemPropsEntity {

    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "itemProps"

    fun writeItem(valueList: List<ItemPropsModel>) {
        var db = sqliteHelper.writableDatabase
        try {

            var insStmnt = db.compileStatement(
                "" + "INSERT INTO "
                        + tableName + " (availabilityPrice,Categoria,Marca,maxPrice,minPrice,Subcategoria,id) "
                        + "VALUES(?,?,?,?,?,?,?);"
            )
            db.beginTransaction()
            for (value in valueList) {
                try {
                    insStmnt.bindString(1, value.availabilityPrice)
                } catch (e: Exception) {
                    insStmnt.bindNull(1)
                }
                try {
                    insStmnt.bindString(2, value.Categoria)
                } catch (e: Exception) {
                    insStmnt.bindNull(2)
                }
                try {
                    insStmnt.bindString(3, value.Marca)
                } catch (e: Exception) {
                    insStmnt.bindNull(3)
                }
                try {
                    insStmnt.bindString(4, value.maxPrice)
                } catch (e: Exception) {
                    insStmnt.bindNull(4)
                }
                try {
                    insStmnt.bindString(5, value.minPrice)
                } catch (e: Exception) {
                    insStmnt.bindNull(5)
                }
                try {
                    insStmnt.bindString(6, value.Subcategoria)
                } catch (e: Exception) {
                    insStmnt.bindNull(6)
                }
                try {
                    insStmnt.bindLong(7, value.id!!)
                } catch (e: Exception) {
                    insStmnt.bindNull(7)
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