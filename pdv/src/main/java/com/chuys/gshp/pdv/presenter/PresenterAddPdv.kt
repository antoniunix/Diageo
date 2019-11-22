package com.chuys.gshp.pdv.presenter

import android.util.Log
import com.chuys.gshp.pdv.presenter.contract.AddPdvContract
import com.chuys.gshp.shared.domain.provider.ContextProvider
import com.chuys.gshp.shared.domain.provider.GeolocationProvider
import com.chuys.gshp.shared.presenter.GeolocationContract
import io.reactivex.disposables.CompositeDisposable

class PresenterAddPdv (val view:AddPdvContract.AddPdvViewContract, val viewgeo:GeolocationContract.GeolocationViewContract,
                      val geolocationDataProvider: GeolocationProvider) : AddPdvContract.AddPdvPresenterContract{

    private val disposables = CompositeDisposable()

    override fun getUserLocation() {
        disposables.add(geolocationDataProvider.getUserLocation().execute(null).subscribe{location->
           if(location!=null){
               viewgeo.showLocation(location)
           }else{
               disposables.dispose()
           }
        })
    }

    override fun getAddress(lat: Double, lon: Double) {
      disposables.add(geolocationDataProvider.getAddres(lat,lon).execute(null).subscribe{
          address->
          if(address!=null){
              Log.e("leo"," "+address.getAddressLine(0))
              viewgeo.setAddres(address)
          }else{
              disposables.dispose()
          }
      })
    }


}