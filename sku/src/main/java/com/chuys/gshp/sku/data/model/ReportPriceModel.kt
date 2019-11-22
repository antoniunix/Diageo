package com.chuys.gshp.sku.data.model

data class ReportPriceModel(
    val idReport: Long,
    val uuid: String,
    val idSku: String,
    val answer: String,
    val lastSync: String
)