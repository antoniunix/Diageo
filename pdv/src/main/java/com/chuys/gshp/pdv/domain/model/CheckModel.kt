package com.chuys.gshp.pdv.domain.model

data class CheckModel(
    val idReport: Long,
    val lat: Double,
    val lon: Double,
    val date: Long,
    val type: Int
)