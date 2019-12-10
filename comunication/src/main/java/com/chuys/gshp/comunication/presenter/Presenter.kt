package com.chuys.gshp.comunication.presenter

import com.chuys.gshp.comunication.domain.provider.MediaProvider
import com.chuys.gshp.comunication.presenter.contract.MediaContract
import io.reactivex.disposables.CompositeDisposable

class Presenter (val view : MediaContract.MediaViewContract, val mediaProvider: MediaProvider):
    MediaContract.MediaPresenterContract{

    private val disposables = CompositeDisposable()

    override fun getAllMedia() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
    }

}