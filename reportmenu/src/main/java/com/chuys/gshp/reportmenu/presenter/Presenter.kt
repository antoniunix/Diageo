package com.chuys.gshp.reportmenu.presenter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.reportmenu.domain.providers.ReportMenuProvider
import com.chuys.gshp.reportmenu.presenter.contract.ReportMenuContract
import com.chuys.gshp.reportmenu.view.ReportMenuAdapter
import io.reactivex.disposables.CompositeDisposable

class Presenter(val view: ReportMenuContract.ViewContract, val reportMenuProvider: ReportMenuProvider) :
    ReportMenuContract.PresenterContract {

    private val disposables = CompositeDisposable()

    override fun getModules() {
        disposables.add(reportMenuProvider.getModulerUseCase().execute(null).subscribe { it ->
            if (it.isSuccess) {
                view.loadRecyclerView(it.data!!)
            } else {
                view.showError()
            }
        })
    }

    override fun clickItemAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        val reportMenuAdapter = adapter as ReportMenuAdapter
        disposables.add(reportMenuAdapter.clickEvent.subscribe {
            Log.e("module", it.name)
        })
    }

    override fun destroy() {
    }

}