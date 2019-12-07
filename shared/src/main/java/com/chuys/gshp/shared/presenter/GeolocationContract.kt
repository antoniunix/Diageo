package com.chuys.gshp.shared.presenter

import android.location.Address
import android.location.Location

interface GeolocationContract {

    interface GeolocationPresenterContract {
        fun getUserLocation( activityRequestCode: Int)
    }

    interface GeolocationViewContract {
        fun showError()
        fun showLocation(location: Location)
        fun setAddres(address: Address)

    }

}