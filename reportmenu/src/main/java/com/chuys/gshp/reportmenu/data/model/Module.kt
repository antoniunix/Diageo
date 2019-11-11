package com.chuys.gshp.reportmenu.data.model

data class Module(
    val id: Long,
    val description: String,
    val form : List<Int>,
    val iconActive: String,
    val iconInactive: String,
    val name: String,
    val order: Int
){
    constructor():this(0,"", listOf(0),"","","",0)
}