package com.chuys.gshp.reportmenu.domain.model

data class Modules(
    val id: Int,
    val name: String,
    val optional: Int,
    val order: Int,
    val complete: Boolean,
    val pathImageIncomplete: String,
    val pathImageComplete: String
)