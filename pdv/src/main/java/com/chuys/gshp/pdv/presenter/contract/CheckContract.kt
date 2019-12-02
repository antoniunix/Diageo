package com.chuys.gshp.pdv.presenter.contract

import android.app.Activity
import android.os.Bundle
import com.chuys.gshp.pdv.data.model.KpiData

interface CheckContract {

    interface CheckPresenterContract{
        fun getUserLocation()
        fun saveCheckReport(activity: Activity,bundle: Bundle,typeCheck:Int)
        fun saveReportReport(idPdv:Long)

    }


    interface CheckViewContract {
        fun getData(kpiData: KpiData)
        fun showError()

    }



}