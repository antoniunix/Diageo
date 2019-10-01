package com.chuys.gshp.pdv.contract

interface PdvContract {
    interface PdvPresenterContract {
        fun getAllPdv()
        fun destroy()
    }

    interface PdvViewContract {
        fun loadRecyclerView()
        fun showError()
    }
}