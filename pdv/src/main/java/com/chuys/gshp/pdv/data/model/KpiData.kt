package com.chuys.gshp.pdv.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class KpiData(
    val additionalExhibitions: Int,
    val effectiveness: Int,
    val incidences: Int,
    val rentedExhibitions: Int,
    val visits: Int,
    val workedHours: String

) : Parcelable {
    constructor() : this(0, 0, 0, 0, 0, "0.0")
}