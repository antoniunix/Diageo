package com.chuys.gshp.poll.domain.model

data class Form(
    val id: Long,
    val description: String,
    val name: String,
    val order: Int,
    val seccion: List<Int>
) {
    constructor() : this(0, "", "", 0, listOf(0))
}