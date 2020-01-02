package com.chuys.gshp.pdv.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PdvModel(
    val id: Int,
    val name: String,
    val idClient: Int,
    val nameClient: String,
    val postalCode: String,
    val code: String,
    val codeNumber: Int,
    val address: String,
    var lat: Double,
    var lon: Double

):Parcelable