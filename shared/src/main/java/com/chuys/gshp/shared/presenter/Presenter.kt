package com.chuys.gshp.shared.presenter

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.*
import androidx.work.OneTimeWorkRequest.Builder
import androidx.work.OneTimeWorkRequest.from
import com.chuys.gshp.shared.data.repository.GeolocationRepository
import com.chuys.gshp.shared.util.workmanager.GeolocationTrackWorkManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import java.util.concurrent.TimeUnit
import androidx.work.WorkInfo



class Presenter (val view : GeolocationContract.GeolocationViewContract, val application: Application):GeolocationContract.GeolocationPresenterContract{

    private  var geolocationRepository:  GeolocationRepository=GeolocationRepository(application)

    override fun isPermissionGranted(requestCode: Int) {
        geolocationRepository.getLocation()
    }


    override fun getUserLocation(activityRequestCode: Int) {
      /*  System.out.println("Init ")
        val uniqueWorkName="uu"
        val workManager=WorkManager.getInstance(application)
        val oneTimeWorkRequest:OneTimeWorkRequest= Builder(GeolocationTrackWorkManager::class.java).addTag(uniqueWorkName).build()
        workManager.enqueue(oneTimeWorkRequest)*/
      //Log.e("leo",""+ workManager.getWorkInfoById(oneTimeWorkRequest.id))

        val periodicRefreshTokenWork = Builder(
            GeolocationTrackWorkManager::class.java).addTag("unique").build()
        WorkManager.getInstance(application).enqueueUniqueWork("unique",ExistingWorkPolicy.REPLACE,periodicRefreshTokenWork)

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