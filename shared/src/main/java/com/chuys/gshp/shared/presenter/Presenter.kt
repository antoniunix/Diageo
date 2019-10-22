package com.chuys.gshp.shared.presenter

import android.app.Activity
import android.app.Application
import com.chuys.gshp.shared.data.repository.GeolocationRepository
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

class Presenter (val view : GeolocationContract.GeolocationViewContract, val application: Application):GeolocationContract.GeolocationPresenterContract{

    private  var geolocationRepository:  GeolocationRepository=GeolocationRepository()


    override fun isPermissionGranted(requestCode: Int) {
        geolocationRepository.getLocation(application)
    }

    override fun getUserLocation(activityRequestCode: Int) {
        geolocationRepository.getLocation(application)
    }

    private fun isGooglePlayServicesAvailable(activity: Activity): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(activity)
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show()
            }
            return false
        }
        return true
    }

}