package com.chuys.gshp.sku.data.repository

import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider
import com.chuys.gshp.sku.data.model.ItemModel
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData

class ItemEntity {

    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "item"

    fun writeItem(valueList: List<ItemModel>) {
        val db = sqliteHelper.writableDatabase
        try {
            val insStmnt = db.compileStatement(
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
        val qry = "SELECT DISTINCT\n" +
                "item.id,\n" +
                "item.name,\n" +
                "itemProps.Categoria,\n" +
                "itemProps.Marca,\n" +
                "itemProps.maxPrice,\n" +
                "itemProps.minPrice,\n" +
                "itemProps.Subcategoria,\n" +
                "availability_report.answer AS availabilityAnswer,\n" +
                "price_report.answer AS priceAnswer\n" +
                "FROM\n" +
                "item\n" +
                "JOIN itemProps\n" +
                "ON item.id = itemProps.id \n" +
                "LEFT JOIN availability_report\n" +
                "ON item.id = availability_report.idSku AND availability_report.idReport=1\n" +
                "LEFT JOIN price_report\n" +
                "ON item.id = price_report.idSku AND price_report.idReport=1"
        var cursor = db.rawQuery(qry, null)
        val obj = ArrayList<SkuAvailabilityAndPriceData>()
        var catalogo: SkuAvailabilityAndPriceData
        if (cursor.moveToFirst()) {
            val id = cursor.getColumnIndexOrThrow("id")
            val name = cursor.getColumnIndexOrThrow("name")
            val Categoria = cursor.getColumnIndexOrThrow("Categoria")
            val Marca = cursor.getColumnIndexOrThrow("Marca")
            val maxPrice = cursor.getColumnIndexOrThrow("maxPrice")
            val minPrice = cursor.getColumnIndexOrThrow("minPrice")
            val Subcategoria = cursor.getColumnIndexOrThrow("Subcategoria")
            val availabilityAnswer = cursor.getColumnIndexOrThrow("availabilityAnswer")
            val priceAnswer = cursor.getColumnIndexOrThrow("priceAnswer")
            do {
                catalogo = SkuAvailabilityAndPriceData(
                    "0",1, cursor.getString(id).toLong(),
                    cursor.getString(name),
                    cursor.getString(Categoria),
                    cursor.getString(Marca),
                    cursor.getString(maxPrice),
                    cursor.getString(minPrice),
                    cursor.getString(Subcategoria),
                    0,
                    cursor.getInt(availabilityAnswer),
                    cursor.getString(priceAnswer),
                    false
                )
                obj.add(catalogo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return obj
    }

    fun deleteAll() {
        var db = sqliteHelper.getReadableDatabase()
        db.execSQL("delete from " + tableName)
    }

}