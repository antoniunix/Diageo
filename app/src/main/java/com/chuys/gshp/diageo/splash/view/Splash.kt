package com.chuys.gshp.diageo.splash.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chuys.gshp.diageo.R
import com.chuys.gshp.diageo.splash.contract.SplashContract
import com.chuys.gshp.diageo.splash.presenter.SplashPresenter

class Splash : AppCompatActivity(),SplashContract.SplashViewContract {

    private lateinit var presenter: SplashContract.SplashPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter=SplashPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.create()
    }

    override fun endTime() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.finish()
    }
}
