package com.chuys.gshp.poll.presenter

import com.chuys.gshp.poll.domain.provider.RealDBProvider
import io.reactivex.disposables.CompositeDisposable

class PresenterRealDB(
    val view: com.chuys.gshp.poll.presenter.contract.RealDBContract.RealDBViewContract,
    val realDBDataProvider: RealDBProvider
) : com.chuys.gshp.poll.presenter.contract.RealDBContract.RealDBPresenterContract {

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