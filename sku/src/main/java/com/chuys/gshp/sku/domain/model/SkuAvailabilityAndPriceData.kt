package com.chuys.gshp.sku.domain.model

data class SkuAvailabilityAndPriceData(
    val id: String,
    val idReport: Long,
    val idSku: Long,
    val name: String,
    val Categoria: String?,
    val Marca: String?,
    val maxPrice: String?,
    val minPrice: String?,
    val Subcategoria: String?,
    val order: Int,
    var availability: Int?,
    var price: String?,
    val preload: Boolean
)