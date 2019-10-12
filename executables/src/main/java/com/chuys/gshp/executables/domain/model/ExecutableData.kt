package com.chuys.gshp.executables.domain.model

data class ExecutableData(
    val id: String,
    val idExecutable: Int,
    val name: String,
    val resume: String,
    val order: Int,
    val done: Boolean,
    val preload: Boolean
)