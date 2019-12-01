package com.chuys.gshp.pdv.domain.model

data class CheckModel(
    val id: Int,
    val idReport: Int,
    val lat: Double,
    val lon: Double,
    val date: String
)