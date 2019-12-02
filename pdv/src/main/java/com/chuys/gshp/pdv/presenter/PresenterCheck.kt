package com.chuys.gshp.pdv.presenter


import android.app.Activity
import android.location.Location
import android.os.Bundle
import android.util.Log
import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.pdv.domain.model.CheckModel
import com.chuys.gshp.pdv.domain.model.ReportReportModel
import com.chuys.gshp.pdv.domain.providers.CheckProvider
import com.chuys.gshp.pdv.domain.providers.KpiProvider
import com.chuys.gshp.pdv.domain.providers.ReportProvider
import com.chuys.gshp.pdv.presenter.contract.CheckContract
import com.chuys.gshp.shared.domain.provider.GeolocationProvider
import com.chuys.gshp.shared.presenter.GeolocationContract
import io.reactivex.disposables.CompositeDisposable

class PresenterCheck(
    val viewCheck: CheckContract.CheckViewContract,
    val viewgeo: GeolocationContract.GeolocationViewContract,
    val geolocationDataProvider: GeolocationProvider,
    val kpiProvider: KpiProvider,
    val checkProvider: CheckProvider,val reportProvider: ReportProvider
) : CheckContract.CheckPresenterContract {


    private val disposable = CompositeDisposable()
    private lateinit var locationCheck:Location
    private var date:Long=0

    override fun getUserLocation() {
        disposable.add(geolocationDataProvider.getUserLocation().execute(null).subscribe { location ->
            if (location != null) {
                viewgeo.showLocation(location)
                locationCheck= Location("")
                locationCheck.set(location)
            } else {
                disposable.dispose()
            }
        })
    }

    override fun saveCheckReport(activity: Activity,bundle: Bundle,typeCheck:Int) {

        val checkModel=CheckModel(0,locationCheck.latitude,locationCheck.longitude,date,typeCheck)
        disposable.add(checkProvider.saveReportCheck().execute(checkModel).subscribe{
            it ->
            if(it.isSuccess){
                Log.e("Save","AHUEO")
            }else{
                Log.e("Save","MAMO")
            }
        })
        ActivityManager.changeToActivitywithBundle(Activities.CHECK, activity, bundle)
    }

    override fun saveReportReport(idPdv: Long) {
        date=System.currentTimeMillis()
        val reportModel=ReportReportModel(0,idPdv,date,0)
        disposable.add(reportProvider.saveReportReport().execute(reportModel).subscribe{
            it->
            if(it.isSuccess){
                Log.e("Save","save")
            }else{
                Log.e("Save","error")
            }
        })
    }

    override fun getReportReport() {
        disposable.add(reportProvider.getReport().execute(null).subscribe{
                it->
            if(it.isSuccess){
                viewCheck.setReportData(it.data!!)
            }else{
                Log.e("Save","error")
            }
        })
    }
    override fun updateReport(activity: Activity, idReeport: Long) {
       date=System.currentTimeMillis()
        val reportModel=ReportReportModel(idReeport,0,0,date)
        disposable.add(reportProvider.updateReport().execute(reportModel).subscribe{
                it->
            if(it.isSuccess){
                Log.e("Save","save")
            }else{
                Log.e("Save","error")
            }
        })

    }

    override fun getKpi(idSite: String) {
        disposable.add(kpiProvider.getData().execute(idSite).subscribe { data ->
            if (data.data!= null && data.isSuccess)
                viewCheck.getData(data.data!!)
            else
                viewCheck.showError()
        })
    }

}