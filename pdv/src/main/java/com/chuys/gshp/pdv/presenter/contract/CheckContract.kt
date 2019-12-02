package com.chuys.gshp.pdv.presenter.contract

import android.app.Activity
import android.os.Bundle
import com.chuys.gshp.pdv.domain.model.ReportReportModel

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

    interface CheckOutContract{
        fun saveCheckout()
    }



}