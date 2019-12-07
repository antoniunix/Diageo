package com.chuys.gshp.pdv.presenter.contract

interface AddPdvContract {

    interface AddPdvPresenterContract {
        fun getUserLocation()
        fun getAddress(lat:Double, lon:Double)
    }

    interface AddPdvViewContract{
        fun setUserLocation()

    }
}