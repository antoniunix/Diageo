package com.chuys.gshp.diageo.splash.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.chuys.gshp.diageo.R
import com.chuys.gshp.diageo.splash.WorkerExample
import com.chuys.gshp.diageo.splash.contract.SplashContract
import com.chuys.gshp.diageo.splash.presenter.SplashPresenter
import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.navigation.ActivityManager
import com.facebook.stetho.Stetho

class Splash : AppCompatActivity(), SplashContract.SplashViewContract {

    private val tag: String? = Splash::class.java.simpleName
    private lateinit var presenter: SplashContract.SplashPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(this)
        Stetho.initializeWithDefaults(this)
        val oneTimeWorkRequest : OneTimeWorkRequest = OneTimeWorkRequest.from(WorkerExample::class.java)
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this, Observer{
                    workInfo ->
                var status =workInfo.state.name
                Log.e("leo","status "+status)
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    Log.e("leo","Work finished!")
                }

            })
    }

    override fun onStart() {
        super.onStart()
        presenter.create()
        presenter.startCountTime()
    }

    override fun endTime() {
        ActivityManager.changeToActivity(Activities.GEOLOCATION, this)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.finish()
    }
}
