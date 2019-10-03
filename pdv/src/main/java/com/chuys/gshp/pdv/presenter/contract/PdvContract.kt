package com.chuys.gshp.pdv.presenter.contract

import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.pdv.domain.model.PdvData

interface PdvContract {
    interface PdvPresenterContract {
        fun getAllPdv()
        fun clickItemAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)
        fun destroy()
    }

    interface PdvViewContract {
        fun loadRecyclerView(pdv: List<PdvData>)
        fun showError()
    }
}