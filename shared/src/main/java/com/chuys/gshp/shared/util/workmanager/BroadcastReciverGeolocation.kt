package com.chuys.gshp.shared.util.workmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.work.*
import androidx.work.impl.WorkManagerInitializer
import java.util.concurrent.TimeUnit

class BroadcastReciverGeolocation : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        System.out.println("Init broadcast")
        /*val uniqueWorkName = "leo"
        val periodicRefreshTokenWork = PeriodicWorkRequest.Builder(
            GeolocationTrackWorkManager::class.java,
            15,
            TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(context!!).enqueueUniquePeriodicWork(
            uniqueWorkName,
            ExistingPeriodicWorkPolicy.REPLACE,
            periodicRefreshTokenWork)
*/


    }

}