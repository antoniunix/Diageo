package com.chuys.gshp.pdv.presenter.contract

import android.app.Activity
import android.os.Bundle
import com.chuys.gshp.pdv.data.model.KpiData
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
        fun getData(kpiData: KpiData)
        fun showError()
    }

    interface CheckOutContract{
        fun saveCheckout()
    }



}