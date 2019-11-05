package com.chuys.gshp.shared.util.workmanager

import android.app.Application
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.chuys.gshp.shared.data.repository.GeolocationRepository

class GeolocationTrackWorkManager(context: Application, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    var repository:GeolocationRepository=GeolocationRepository(context)

    override fun doWork(): Result {
       Log.e("leo","do work init")
       return try {
           Log.e("leo","do work init1")
           repository.getLocation()
           Result.success()
       }catch (e : Exception){
           System.out.println("Init do work fall")
           Result.failure()
       }
    }

    override fun onStopped() {
        super.onStopped()
    }


}