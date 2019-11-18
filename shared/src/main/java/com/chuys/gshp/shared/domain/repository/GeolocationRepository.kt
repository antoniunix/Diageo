package com.chuys.gshp.shared.domain.repository

import android.location.Location

interface GeolocationRepository {
    fun getLocation(): Location
}