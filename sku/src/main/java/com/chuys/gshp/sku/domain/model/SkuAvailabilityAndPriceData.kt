package com.chuys.gshp.sku.domain.model

data class SkuAvailabilityAndPriceData(
    val id: String,
    val idSku: Int,
    val name: String,
    val category: String,
    val order: Int,
    val availability: Boolean,
    val price: Float,
    val preload: Boolean
)