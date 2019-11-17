package com.chuys.gshp.pdv.domain.model

data class PdvModel(
    val id: Int,
    val name: String,
    val idClient: Int,
    val nameClient: String,
    val postalCode: String,
    val code: String,
    val codeNumber: Int,
    val address: String,
    val lat: Double,
    val lon: Double

)