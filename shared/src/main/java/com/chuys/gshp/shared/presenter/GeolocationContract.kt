package com.chuys.gshp.shared.presenter

import android.app.Activity
import android.location.Location

interface GeolocationContract {

    interface GeolocationPresenterContract {
        fun getUserLocation( activityRequestCode: Int)
    }

    interface GeolocationViewContract {
        fun showError()
        fun showLocation(location: Location)
    }

}