package com.chuys.gshp.visits.view.domain.model

data class VisitModel(
val idReport:Long,
val idPdv: Long,
val dateCheckin: Long,
val dateCheckout: Long,
val type: Int,
val uuid: String,
val send: Int,
val pdvName: String
)