package com.chuys.gshp.pdv.domain.model

data class PdvData(
    val id: Int,
    val name: String,
    val idClient: Int,
    val nameClient: String,
    val postalCode: String,
    val code: String,
    val codeNumber: Int,
    val externalNumber: String,
    val internalNumber: String,
    val street: String,
    val streetLeft: String,
    val streetRight: String,
    val territory: String,
    val lat: Double,
    val lon: Double

)