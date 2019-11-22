package com.chuys.gshp.shared.presenter

import com.chuys.gshp.shared.domain.provider.RealDBProvider
import io.reactivex.disposables.CompositeDisposable

class PresenterRealDB(
    val view: RealDBContract.RealDBViewContract,
    val realDBDataProvider: RealDBProvider
) : RealDBContract.RealDBPresenterContract {

    private val disposable = CompositeDisposable()

    override fun getData() {
        disposable.add(realDBDataProvider.getData().execute(null).subscribe { data ->
            if (data != null) {
                view.show(data)
            } else {
                view.showError("No se pudo cargar la información")
                disposable.dispose()
            }
        })
    }
}