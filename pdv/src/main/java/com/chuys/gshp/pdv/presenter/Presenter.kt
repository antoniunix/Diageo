package com.chuys.gshp.pdv.presenter

import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.pdv.contract.PdvContract
import com.chuys.gshp.pdv.domain.model.PdvData
import com.chuys.gshp.pdv.domain.providers.PdvProvider
import com.chuys.gshp.pdv.view.ListPdvAdapter
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class Presenter(val view: PdvContract.PdvViewContract, val pdvProvider: PdvProvider) :
    PdvContract.PdvPresenterContract {


    private val disposables = CompositeDisposable()

    override fun getAllPdv() {
        disposables.add(pdvProvider.getListPdvUseCase().execute(null).subscribe { it ->
            if (it.isSuccess) {
                view.loadRecyclerView(it.data!!)
            } else {
                view.showError()
            }
        })
    }

    override fun clickItemAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        val listPdvAdapter=adapter as ListPdvAdapter
        disposables.add(listPdvAdapter.clickEvent.subscribe {
            Log.e("TAG","Clicked on ${it.name}")
        })
    }

    override fun destroy() {
        disposables.dispose()
    }
}