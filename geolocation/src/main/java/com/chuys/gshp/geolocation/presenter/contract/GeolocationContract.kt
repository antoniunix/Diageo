package com.chuys.gshp.geolocation.presenter.contract

interface GeolocationContract {

    interface GeolocationPresenterContract{
        fun clickBLocation()
        fun destroy()
    }

    interface GeolocationViewContract{
        fun showError()
    }
}