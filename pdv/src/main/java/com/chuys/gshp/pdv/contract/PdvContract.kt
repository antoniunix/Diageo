package com.chuys.gshp.pdv.contract

interface PdvContract {
    interface PdvPresenterContract {
        fun start()
        fun getAllPdv()
        fun destroy()
    }

    interface PdvViewContract {


    }
}