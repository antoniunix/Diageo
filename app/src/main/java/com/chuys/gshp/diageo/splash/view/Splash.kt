package com.chuys.gshp.diageo.splash.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chuys.gshp.diageo.R
import com.chuys.gshp.diageo.splash.contract.SplashContract
import com.chuys.gshp.diageo.splash.presenter.SplashPresenter
import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.navigation.ActivityManager
import kotlinx.android.synthetic.main.activity_splash.*


class Splash : AppCompatActivity(), SplashContract.SplashViewContract {

    private val tag: String? = Splash::class.java.simpleName
    private lateinit var presenter: SplashContract.SplashPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(this)
        splashAnimImageView.setImageResource(R.drawable.ic_launcher)
    }

    override fun onStart() {
        super.onStart()
        presenter.create()
        presenter.startCountTime()
    }

    override fun endTime() {
        ActivityManager.changeToActivity(Activities.LOGIN, this)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.finish()
    }
}
