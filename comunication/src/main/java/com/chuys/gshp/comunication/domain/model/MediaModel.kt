package com.chuys.gshp.comunication.domain.model

data class MediaModel(
    var id: Int?,
    val title: String,
    val url: String,
    val description: String,
    val mediaType: Int
)