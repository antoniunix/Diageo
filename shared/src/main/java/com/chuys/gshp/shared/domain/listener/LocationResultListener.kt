package com.chuys.gshp.shared.domain.listener

import android.location.Location

interface LocationResultListener {
    fun locationResult(location: Location)
}