package com.chuys.gshp.geolocation.domain.usescases

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.chuys.gshp.geolocation.domain.repository.LocationRepository

class TrackLocationManager (context: Context, workertParams:WorkerParameters):Worker(context,workertParams){

    lateinit var repository: LocationRepository

    override fun doWork(): Result {
        return try {
            repository.getLocation()
            Result.success()
        }catch (e: Exception) {
            Result.failure()
        }

    }


}