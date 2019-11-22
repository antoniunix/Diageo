package com.chuys.gshp.sku.presenter.contract

import com.chuys.gshp.sku.domain.model.SkuAvailabilityAndPriceData

interface AvailabilityAndPriceContract {
    interface PresenterContract {
        fun getMeasurementSku()
        fun saveReport()
        fun destroy()
    }

    interface ViewContract {
        fun loadRecyclerView(sku: List<SkuAvailabilityAndPriceData>)
        fun finishReport()
        fun showError()

    }
}