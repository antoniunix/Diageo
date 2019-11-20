package com.chuys.gshp.shared.util.workmanager

import android.content.ContentProvider
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.chuys.gshp.shared.data.provider.ContextDataProvider
import com.chuys.gshp.shared.data.repository.GeolocationDataRepository

class GeolocationTrackWorkManager(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    var contentProvider=ContextDataProvider(context)
   var dataRepository:GeolocationDataRepository=GeolocationDataRepository(contentProvider)

    override fun doWork(): Result {
       return try {
           dataRepository.getLocation()
           Result.success()
       }catch (e : Exception){
           Result.failure()
       }
    }

}