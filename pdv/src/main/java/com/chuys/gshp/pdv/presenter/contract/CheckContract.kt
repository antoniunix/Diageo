package com.chuys.gshp.pdv.presenter.contract

import android.app.Activity
import android.os.Bundle
<<<<<<< HEAD
import com.chuys.gshp.pdv.data.model.KpiData
=======
>>>>>>> Save report, uopdate report, save checkin and checkout
import com.chuys.gshp.pdv.domain.model.ReportReportModel

interface CheckContract {

    interface CheckPresenterContract{
        fun getUserLocation()
        fun getKpi(idSite: String)
        fun saveCheckReport(activity: Activity,bundle: Bundle,typeCheck:Int)
        fun updateReport(activity: Activity, idReeport:Long)
        fun saveReportReport(idPdv:Long)
        fun getReportReport()

    }


    interface CheckViewContract {
        fun setReportData(reportReportModel: ReportReportModel)
<<<<<<< HEAD
        fun getData(kpiData: KpiData)
        fun showError()
=======
>>>>>>> Save report, uopdate report, save checkin and checkout
    }

    interface CheckOutContract{
        fun saveCheckout()
    }



}