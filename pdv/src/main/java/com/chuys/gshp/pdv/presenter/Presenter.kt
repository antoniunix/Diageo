package com.chuys.gshp.pdv.presenter

import com.chuys.gshp.pdv.contract.PdvContract
import com.chuys.gshp.pdv.domain.providers.PdvProvider
import io.reactivex.disposables.CompositeDisposable

class Presenter(val view: PdvContract.PdvViewContract, val pdvProvider: PdvProvider) :
    PdvContract.PdvPresenterContract {

    private val disposables = CompositeDisposable()

    override fun getAllPdv() {
        disposables.add(pdvProvider.getListPdvUseCase().execute(null).subscribe { it ->
            if (it.isSuccess) {
                view.loadRecyclerView()
            } else {
                view.showError()
            }
        })
    }

    override fun destroy() {
        disposables.dispose()
    }
}