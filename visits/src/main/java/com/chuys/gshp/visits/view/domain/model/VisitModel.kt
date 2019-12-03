package com.chuys.gshp.visits.view.domain.model

data class VisitModel(
val idReport:Long,
val idPdv: Long,
val date_checkin: Long,
val date_checkout: Long,
val type: Int
)