package com.chuys.gshp.shared.domain.models

import java.util.*

data class ReportData(
    var idReport: Int,
    var uuid: UUID,
    var idPdv: Int,
    var date: Long
)