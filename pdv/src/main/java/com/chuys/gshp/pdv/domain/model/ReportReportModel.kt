package com.chuys.gshp.pdv.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReportReportModel(
    val idReport: Long,
    val idPdv: Long,
    val date_checkin: Long,
    val date_checkout: Long
):Parcelable