package com.chuys.gshp.sku.presenter

import android.util.Log
import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData
import com.chuys.gshp.sku.domain.provider.AvailabilityAndPriceProvider
import com.chuys.gshp.sku.presenter.contract.AvailabilityAndPriceContract
import io.reactivex.disposables.CompositeDisposable

class Presenter constructor(
    val view: AvailabilityAndPriceContract.ViewContract,
    val provider: AvailabilityAndPriceProvider
) : AvailabilityAndPriceContract.PresenterContract {

    private lateinit var skuList: List<SkuAvailabilityAndPriceData>
    private val disposables = CompositeDisposable()

    override fun getMeasurementSku() {
        disposables.add(provider.getSkuToMeasurementAvailabilityAndPriceUseCase().execute(null).subscribe { it ->
            if (it.isSuccess) {
                skuList = it.data!!
                view.loadRecyclerView(skuList)
            } else {
                view.showError()
            }
        })
    }

    override fun saveReport() {
        disposables.add(provider.saveReportSku().execute(skuList).subscribe{ it->
            if (it.isSuccess) {
                view.finishReport()
            } else {
                view.showError()
            }
        })
    }

    override fun destroy() {
        disposables.dispose()
    }

}