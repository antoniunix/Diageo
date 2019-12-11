package com.chuys.gshp.visits.view.presenter.contract

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.visits.view.domain.model.VisitModel

interface VisitContract {

    interface VisitPresenterContract{
        fun getAllReports()
        fun clickItemAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, activity: Activity)
        fun destroy()
    }

    interface VisitViewContractt{
        fun loadRecyclerView(visit:List<VisitModel>)
        fun showError()

    }

}