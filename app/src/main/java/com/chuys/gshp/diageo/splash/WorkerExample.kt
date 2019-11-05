package com.chuys.gshp.diageo.splash

import android.app.Application
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.contracts.Returns

class WorkerExample (context: Application, workerParams: WorkerParameters) :
    Worker(context, workerParams){

    override fun doWork(): Result {
       Log.e("leo","ya corrio")
        return Result.success()
    }


}