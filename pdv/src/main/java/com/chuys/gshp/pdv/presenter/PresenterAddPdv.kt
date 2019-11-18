package com.chuys.gshp.pdv.presenter

import com.chuys.gshp.pdv.presenter.contract.AddPdvContract
import com.chuys.gshp.shared.domain.provider.ContextProvider
import com.chuys.gshp.shared.domain.provider.GeolocationProvider
import com.chuys.gshp.shared.presenter.GeolocationContract
import io.reactivex.disposables.CompositeDisposable

class PresenterAddPdv (val view:AddPdvContract.AddPdvViewContract, val viewgeo:GeolocationContract.GeolocationViewContract,
                      val geolocationDataProvider: GeolocationProvider) : AddPdvContract.AddPdvPresenterContract{

    private val disposables = CompositeDisposable()


    override fun getUserLocation() {
        if()
        disposables.add(geolocationDataProvider.getUserLocation().execute(null).subscribe{location->
            viewgeo.showLocation(location)
        })
    }

}