package com.chuys.gshp.login.domain.model

data class SessionData(
    val userName:String,
    val idRoll:Int,
    val roll:String,
    val cookie:String,
    val errorMessage:String
)