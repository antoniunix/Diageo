package com.chuys.gshp.pdv.presenter

import com.chuys.gshp.pdv.contract.PdvContract
import com.chuys.gshp.pdv.domain.providers.PdvProvider

class Presenter(val view: PdvContract.PdvViewContract, val pdvProvider: PdvProvider) :
    PdvContract.PdvPresenterContract {

    override fun start() {
    }

    override fun getAllPdv() {
        pdvProvider.getListPdvUseCase().execute(null).subscribe()
    }


    override fun destroy() {
    }


}