package com.chuys.gshp.reportmenu.domain.model

import com.chuys.gshp.navigation.Activities

data class Modules(
    val id: Long,
    val name: String,
    val optional: Int,
    val order: Int,
    var complete: Boolean,
    val pathImageIncomplete: String,
    val pathImageComplete: String,
    val codeModule: Activities
)