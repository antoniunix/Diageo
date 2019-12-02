package com.chuys.gshp.pdv.presenter


import android.app.Activity
import android.os.Bundle
import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.pdv.domain.providers.CheckProvider
import com.chuys.gshp.pdv.presenter.contract.CheckContract
import com.chuys.gshp.shared.domain.provider.GeolocationProvider
import com.chuys.gshp.shared.presenter.GeolocationContract
import io.reactivex.disposables.CompositeDisposable

class PresenterCheck(
    val checkContract: CheckContract.CheckViewContract,
    val viewgeo: GeolocationContract.GeolocationViewContract,
    val geolocationDataProvider: GeolocationProvider,
    val checkProvider: CheckProvider
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

    override fun saveCheckReport(activity: Activity,bundle: Bundle) {
        ActivityManager.changeToActivitywithBundle(Activities.CHECK, activity, bundle)


    }

}