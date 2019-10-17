package com.chuys.gshp.executables.domain.model

data class ExecutableData(
    val id: String,
    val idExecutable: Int,
    val name: String,
    val resume: String,
    val order: Int,
    var done: Int,
    val preload: Boolean,
    val numPhotos: Int,
    var donePhotos: Int
)