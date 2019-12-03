package com.chuys.gshp.pdv.presenter


import android.app.Activity
import android.os.Bundle
import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.pdv.domain.providers.CheckProvider
import com.chuys.gshp.pdv.domain.providers.KpiProvider
import com.chuys.gshp.pdv.presenter.contract.CheckContract
import com.chuys.gshp.shared.domain.provider.GeolocationProvider
import com.chuys.gshp.shared.presenter.GeolocationContract
import io.reactivex.disposables.CompositeDisposable

class PresenterCheck(
    val checkView: CheckContract.CheckViewContract,
    val viewgeo: GeolocationContract.GeolocationViewContract,
    val geolocationDataProvider: GeolocationProvider,
    val checkProvider: CheckProvider,
    val kpiProvider: KpiProvider
) : CheckContract.CheckPresenterContract {

    private val disposable = CompositeDisposable()

    override fun getUserLocation() {
        disposable.add(geolocationDataProvider.getUserLocation().execute(null).subscribe { location ->
            if (location != null) {
                viewgeo.showLocation(location)
            } else {
                disposable.dispose()
            }
        })
    }

    override fun saveCheckReport(activity: Activity, bundle: Bundle) {
        ActivityManager.changeToActivitywithBundle(Activities.CHECK, activity, bundle)


    }

    override fun getKpi(idSite: String) {
        disposable.add(kpiProvider.getData().execute(idSite).subscribe { it ->
            if (it.isSuccess)
                checkView.getData(it.data!!)
            else
                checkView.showError()
        })
    }

}