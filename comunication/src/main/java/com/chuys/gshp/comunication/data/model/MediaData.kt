package com.chuys.gshp.comunication.data.model

data class MediaData(
    var id:Long?,
    val description: String="",
    val mediaType: Int=0,
    val title: String="",
    val url: String=""
)