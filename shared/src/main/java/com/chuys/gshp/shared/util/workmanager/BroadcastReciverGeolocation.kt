package com.chuys.gshp.shared.util.workmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.chuys.gshp.shared.util.extension.checkLocationPermission
import com.chuys.gshp.shared.util.extension.isGPSEnabled
import java.util.concurrent.TimeUnit

class BroadcastReciverGeolocation : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if(context.isGPSEnabled() && context.checkLocationPermission()){
            val periodicRefreshTokenWork = PeriodicWorkRequest.Builder(GeolocationTrackWorkManager::class.java,
                PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
                TimeUnit.MILLISECONDS).addTag("unique").build()
            WorkManager.getInstance(context).enqueueUniquePeriodicWork("unique",
                ExistingPeriodicWorkPolicy.REPLACE,periodicRefreshTokenWork)
        }else{
            Log.e("error","no hay permisos")
        }
    }

}