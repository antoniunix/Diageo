package com.chuys.gshp.visits.view.data.repository

import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider

class VisitsEntity{
    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName = "item"
}
