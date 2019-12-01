package com.chuys.gshp.pdv.presenter

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.pdv.domain.providers.PdvProvider
import com.chuys.gshp.pdv.presenter.contract.PdvContract
import com.chuys.gshp.pdv.view.ListPdvAdapter
import com.chuys.gshp.shared.domain.constant.StringConstant
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

    override fun clickItemAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, activity: Activity) {
        val bundle=Bundle()
        val listPdvAdapter = adapter as ListPdvAdapter
        disposables.add(listPdvAdapter.clickEvent.subscribe {it->
            bundle.putParcelable(StringConstant.KEYBUNDLE,it)
            ActivityManager.changeToActivitywithBundle(Activities.CHECK, activity,bundle)


        })
    }

    override fun destroy() {
        disposables.dispose()
    }
}