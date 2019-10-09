package com.chuys.gshp.reportmenu.presenter.contract

import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.reportmenu.domain.model.Modules

interface ReportMenuContract {
    interface PresenterContract {
        fun clickItemAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)
        fun getModules()
        fun destroy()
    }

    interface ViewContract {
        fun loadRecyclerView(pdv: List<Modules>)
        fun showError()
        fun goToReportMenu(modules: Modules)
    }
}