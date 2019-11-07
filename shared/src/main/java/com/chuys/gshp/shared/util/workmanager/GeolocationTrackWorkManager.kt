package com.chuys.gshp.shared.util.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.chuys.gshp.shared.data.repository.GeolocationRepository

class GeolocationTrackWorkManager(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    var repository:GeolocationRepository=GeolocationRepository(context)

    override fun doWork(): Result {
       Log.e("leo","do work init")
       return try {
           Log.e("leo","do work init1")
//           repository.getLocation() debes de confirmar que tienes el permiso de location
           Result.success()
       }catch (e : Exception){
           System.out.println("Init do work fall")
           Result.failure()
       }
    }

}