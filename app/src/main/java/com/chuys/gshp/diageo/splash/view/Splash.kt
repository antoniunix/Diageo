package com.chuys.gshp.diageo.splash.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chuys.gshp.diageo.R
import com.chuys.gshp.diageo.activityProvider.ActivityManager
import com.chuys.gshp.diageo.splash.contract.SplashContract
import com.chuys.gshp.diageo.splash.presenter.SplashPresenter
import com.chuys.gshp.shared.domain.constant.Activities

class Splash : AppCompatActivity(), SplashContract.SplashViewContract {

    private val tag: String? = Splash::class.java.simpleName
    private lateinit var presenter: SplashContract.SplashPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.create()
        presenter.startCountTime()
    }

    override fun endTime() {
        ActivityManager.changeToActivity(Activities.MENU_REPORT,this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.finish()
    }
}
