package com.chuys.gshp.comunication.view.data.repository

import com.chuys.gshp.shared.data.database.sqlite.provider.SqliteDBProvider

class MediaTypeEntity {

    private val sqliteHelper = SqliteDBProvider.SqliteDBHelper.getDbHelper()
    private val tableName="media_type"
}