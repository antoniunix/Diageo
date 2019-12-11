package com.chuys.gshp.visits.view.presenter

import android.app.Activity
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.visits.view.domain.model.VisitModel
import com.chuys.gshp.visits.view.domain.provider.VisitProvider
import com.chuys.gshp.visits.view.presenter.contract.VisitContract
import com.chuys.gshp.visits.view.view.VisitAdapter
import io.reactivex.disposables.CompositeDisposable

class Presenter(
    val view: VisitContract.VisitViewContractt,
    val provider: VisitProvider
) : VisitContract.VisitPresenterContract {

    private lateinit var visitList: List<VisitModel>
    private val disposables = CompositeDisposable()

    override fun getAllReports() {
        disposables.add(provider.getVisitUseCase().execute(null).subscribe { it ->
            if (it.isSuccess) {
                visitList = it.data!!
                view.loadRecyclerView(visitList)
            } else {
                view.showError()
            }
        })
    }

    override fun clickItemAdapter(
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
        activity: Activity
    ) {
        val listVisitAdapter = adapter as VisitAdapter
        disposables.add(listVisitAdapter.clickEvent.subscribe { it ->

        })

    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}