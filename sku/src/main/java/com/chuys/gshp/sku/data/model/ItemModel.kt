package com.chuys.gshp.sku.data.model

data class ItemModel(
    val description: String,
    val id: Long,
    val itemTypeId: Long,
    val name: String
) {
    constructor() : this("", 0, 0, "")
}