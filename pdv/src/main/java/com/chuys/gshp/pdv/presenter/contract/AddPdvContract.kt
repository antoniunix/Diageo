package com.chuys.gshp.pdv.presenter.contract

import android.location.Location

interface AddPdvContract {

    interface AddPdvPresenterContract {
        fun getUserLocation()
        fun destroy()

    }

    interface AddPdvViewContract{

    }
}