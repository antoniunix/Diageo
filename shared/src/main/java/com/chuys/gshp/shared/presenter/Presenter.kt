package com.chuys.gshp.shared.presenter


class Presenter (val view : GeolocationContract.GeolocationViewContract):GeolocationContract.GeolocationPresenterContract{


    override fun getUserLocation(activityRequestCode: Int) {
        }


   /* private fun isGooglePlayServicesAvailable(activity: Activity): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(activity)
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show()
            }
            return false
        }
        return true
    }*/

}