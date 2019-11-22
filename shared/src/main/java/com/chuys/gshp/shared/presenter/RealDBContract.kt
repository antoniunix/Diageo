package com.chuys.gshp.shared.presenter

import com.chuys.gshp.shared.data.model.Form

interface RealDBContract {
    interface RealDBPresenterContract {
        fun getData()
    }

    interface RealDBViewContract {
        fun show(data: List<Form>)
        fun showError(error: String)
    }
}