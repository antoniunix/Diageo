package com.chuys.gshp.sku.data.model

data class ReportAvailabilityModel(
    val idReport: Long,
    val uuid: String,
    val idSku: String,
    val answer: Int,
    val lastSync: String
)
