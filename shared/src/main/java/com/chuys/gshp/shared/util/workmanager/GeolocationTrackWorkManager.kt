package com.chuys.gshp.shared.util.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.chuys.gshp.shared.data.repository.GeolocationRepository

class GeolocationTrackWorkManager(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {


    private lateinit var repository: GeolocationRepository

    override fun doWork(): Result {
       return try {
          // repository.getLocation()
           Result.success()
       }catch (e : Exception){
           Result.failure()
       }
    }


}