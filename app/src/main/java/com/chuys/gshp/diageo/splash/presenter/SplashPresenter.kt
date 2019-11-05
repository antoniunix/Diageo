package com.chuys.gshp.diageo.splash.presenter

import android.util.Log
import com.chuys.gshp.diageo.splash.contract.SplashContract
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SplashPresenter(var view: SplashContract.SplashViewContract) : SplashContract.SplashPresenterContract {
    private lateinit var disposable: Disposable
    private val timeOutSplash = 3L

    override fun create() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startCountTime() {
     /*   disposable = Completable.complete()
            .delay(timeOutSplash, TimeUnit.SECONDS, Schedulers.io())
            .subscribeWith(object : DisposableCompletableObserver() {
                override fun onError(error: Throwable) {
                    error.printStackTrace()
                }

                override fun onComplete() {
                    view.endTime()
                }
            })*/
    }

    override fun finish() {
        disposable.dispose()
    }

}