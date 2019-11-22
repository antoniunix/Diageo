package com.chuys.gshp.sku.data.model

data class ItemPropsModel(
    var id: Long?,
    val availabilityPrice: String,
    val Categoria: String,
    val Marca: String,
    val maxPrice: String,
    val minPrice: String,
    val Subcategoria: String
) {
    constructor() : this(0,"", "", "", "", "", "")
}