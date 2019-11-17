package com.chuys.gshp.pdv.data.model

data class PdvData(
    val address: String,
    val code: String,
    val lat: Double,
    val lon: Double,
    val name: String
) {
    constructor() : this("", "", 0.0, 0.0, "")
}