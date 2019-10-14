package com.chuys.gshp.geolocation.presenter.contract

import android.app.Activity
import android.location.Location

interface GeolocationContract {

    interface GeolocationPresenterContract {
        fun getUserLocation(activity: Activity, activityRequestCode: Int)
        fun isPermissionGranted(activity: Activity,requestCode: Int)
      }

    interface GeolocationViewContract {
        fun showError()
        fun showLocation(location: Location)
    }
}