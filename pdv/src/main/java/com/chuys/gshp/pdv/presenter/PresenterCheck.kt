package com.chuys.gshp.pdv.presenter

import com.chuys.gshp.pdv.presenter.contract.CheckContract
import com.chuys.gshp.shared.domain.provider.GeolocationProvider
import com.chuys.gshp.shared.presenter.GeolocationContract
import io.reactivex.disposables.CompositeDisposable

class PresenterCheck (val checkContract: CheckContract.CheckViewContract, val viewgeo: GeolocationContract.GeolocationViewContract,
                      val geolocationDataProvider: GeolocationProvider) : CheckContract.CheckPresenterContract {

    private val disposable = CompositeDisposable()

    override fun getUserLocation() {
        disposable.add(geolocationDataProvider.getUserLocation().execute(null).subscribe{
            location->
            if(location!=null){
                viewgeo.showLocation(location)
            }else{
                disposable.dispose()
            }
        })
    }

}