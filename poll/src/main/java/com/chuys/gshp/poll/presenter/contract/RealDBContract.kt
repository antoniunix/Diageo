package com.chuys.gshp.poll.presenter.contract

interface RealDBContract {
    interface RealDBPresenterContract {
        fun getData()
    }

    interface RealDBViewContract {
        fun show(data: List<com.chuys.gshp.poll.domain.model.Form>)
        fun showError(error: String)
    }
}