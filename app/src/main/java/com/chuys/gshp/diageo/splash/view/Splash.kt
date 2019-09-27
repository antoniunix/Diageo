package com.chuys.gshp.diageo.splash.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.chuys.gshp.diageo.R
import com.chuys.gshp.diageo.splash.contract.SplashContract
import com.chuys.gshp.diageo.splash.presenter.SplashPresenter

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
        Log.d(tag, "change to other module")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.finish()
    }
}