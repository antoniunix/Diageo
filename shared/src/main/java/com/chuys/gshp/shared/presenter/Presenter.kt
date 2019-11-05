package com.chuys.gshp.shared.presenter

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.work.*
import androidx.work.OneTimeWorkRequest.Builder
import com.chuys.gshp.shared.data.repository.GeolocationRepository
import com.chuys.gshp.shared.util.workmanager.GeolocationTrackWorkManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import java.util.concurrent.TimeUnit

class Presenter (val view : GeolocationContract.GeolocationViewContract, val application: Application):GeolocationContract.GeolocationPresenterContract{

    private  var geolocationRepository:  GeolocationRepository=GeolocationRepository(application)


    override fun isPermissionGranted(requestCode: Int) {
        geolocationRepository.getLocation()
    }


    override fun getUserLocation(activityRequestCode: Int) {
        System.out.println("Init broadcast")

        val workManager=WorkManager.getInstance(application)
        val oneTimeWorkRequest = Builder(GeolocationTrackWorkManager::class.java).addTag("leo1")
        workManager.enqueueUniqueWork("leo",ExistingWorkPolicy.KEEP,oneTimeWorkRequest.build())
        Log.e("leo",""+WorkManager.getInstance(application).getWorkInfosByTag("leo1").toString())

      /* val uniqueWorkName = "${application.packageName}"
        val periodicRefreshTokenWork = Builder(
            GeolocationTrackWorkManager::class.java).build()

        WorkManager.getInstance(application).enqueue(periodicRefreshTokenWork)

*/
        // geolocationRepository.getLocation()
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