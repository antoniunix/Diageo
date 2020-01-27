package com.chuys.gshp.comunication.data.model

data class MediaTypeData(
    var id: Int?,
    val ext: String,
    val name: String
){
    constructor():this(0,"","")
}