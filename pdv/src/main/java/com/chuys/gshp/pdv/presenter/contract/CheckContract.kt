package com.chuys.gshp.pdv.presenter.contract

import android.app.Activity
import android.os.Bundle
import com.chuys.gshp.pdv.domain.model.ReportReportModel
import com.chuys.gshp.pdv.data.model.KpiData

interface CheckContract {

    interface CheckPresenterContract{
        fun getUserLocation()
        fun saveCheckReport(activity: Activity,bundle: Bundle,typeCheck:Int)
        fun updateReport(activity: Activity, idReeport:Long)
        fun saveReportReport(idPdv:Long)
        fun getReportReport()

    }


    interface CheckViewContract {
        fun setReportData(reportReportModel: ReportReportModel)
    }
        fun getData(kpiData: KpiData)
        fun showError()

    interface CheckOutContract{
        fun saveCheckout()
    }



}