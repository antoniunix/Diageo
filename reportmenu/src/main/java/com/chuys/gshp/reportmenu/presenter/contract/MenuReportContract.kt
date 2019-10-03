package com.chuys.gshp.reportmenu.presenter.contract

interface MenuReportContract{
    interface PresenterContract{
        fun clickItemAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)
        fun destroy()
    }
    interface ViewContract{
        fun loadRecyclerView(pdv: List<PdvData>)
        fun showError()
    }
}