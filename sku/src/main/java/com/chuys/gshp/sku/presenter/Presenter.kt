package com.chuys.gshp.sku.presenter

import com.chuys.gshp.sku.domain.provider.AvailabilityAndPriceProvider
import com.chuys.gshp.sku.presenter.contract.AvailabilityAndPriceContract
import io.reactivex.disposables.CompositeDisposable

class Presenter constructor(
    val view: AvailabilityAndPriceContract.ViewContract,
    val provider: AvailabilityAndPriceProvider)
    : AvailabilityAndPriceContract.PresenterContract {

    private val disposables = CompositeDisposable()

    override fun getMeasurementSku() {
        disposables.add(provider.getSkuToMeasurementAvailabilityAndPriceUseCase().execute(null).subscribe { it ->
            if (it.isSuccess) {
                view.loadRecyclerView(it.data!!)
            } else {
                view.showError()
            }
        })
    }

    override fun saveReport() {
    }

    override fun destroy() {
        disposables.dispose()
    }

}