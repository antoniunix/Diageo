package com.chuys.gshp.geolocation.presenter

import com.chuys.gshp.geolocation.presenter.contract.GeolocationContract
import io.reactivex.disposables.CompositeDisposable

class Presenter (val view: GeolocationContract.GeolocationViewContract) : GeolocationContract.GeolocationPresenterContract {

    private val disposable = CompositeDisposable()

    override fun clickBLocation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}