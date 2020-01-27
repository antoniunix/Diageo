package com.chuys.gshp.comunication.domain.model

data class ComunicationModel(
    val nameFile: String,
    val title: String,
    val url: String,
    val description: String,
    val mediaType: String,
    val existFile: Boolean
)