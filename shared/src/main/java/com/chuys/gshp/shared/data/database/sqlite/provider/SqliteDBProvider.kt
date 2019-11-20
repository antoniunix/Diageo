package com.chuys.gshp.shared.data.database.sqlite.provider

import android.database.sqlite.SQLiteOpenHelper

class SqliteDBProvider {
    companion object SqliteDBHelper {

        lateinit var sqliteHelper: SQLiteOpenHelper

        fun setDbHelper(sqliteHelper: SQLiteOpenHelper) {
            this.sqliteHelper = sqliteHelper
        }

        fun getDbHelper(): SQLiteOpenHelper {
            return sqliteHelper
        }

    }
}