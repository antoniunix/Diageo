package com.chuys.gshp.sku.domain.model

data class SkuAvailabilityAndPriceData(
    val id: String,
    val idSku: Int,
    val name: String,
    val category: String,
    val order: Int,
    var availability: Int,
    var price: String,
    val preload: Boolean
)