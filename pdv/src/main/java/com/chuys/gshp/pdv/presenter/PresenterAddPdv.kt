package com.chuys.gshp.pdv.presenter

import com.chuys.gshp.pdv.presenter.contract.AddPdvContract
import com.chuys.gshp.shared.domain.listener.LocationResultListener
import com.chuys.gshp.shared.domain.provider.ContextProvider
import com.chuys.gshp.shared.domain.provider.GeolocationProvider
import com.chuys.gshp.shared.presenter.GeolocationContract
import io.reactivex.disposables.CompositeDisposable

class PresenterAddPdv (val view:AddPdvContract.AddPdvViewContract, val viewgeo:GeolocationContract.GeolocationViewContract,
                      val geolocationDataProvider: GeolocationProvider) : AddPdvContract.AddPdvPresenterContract{

    private val disposables = CompositeDisposable()


    override fun getUserLocation() {
        geolocationDataProvider.getUserLocation()
           // System.out.println("location "+location.longitude+" "+location.latitude)
    /*    disposables.add(geolocationDataProvider.getUserLocationCase().execute(null).subscribe{
            it->
            if(it.isSuccess){
                it.data?.let { it1 -> viewgeo.showLocation(it1) }
            }else{
                viewgeo.showError()
            }
        })*/
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}