package com.chuys.gshp.pdv.presenter.contract

import android.app.Activity
import android.os.Bundle

interface CheckContract {

    interface CheckPresenterContract{
        fun getUserLocation()
        fun saveCheckReport(activity: Activity,bundle: Bundle)

    }


    interface CheckViewContract {

    }



}