package com.chuys.gshp.pdv.contract

import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.pdv.domain.model.PdvData
import com.chuys.gshp.pdv.view.ListPdvAdapter
import io.reactivex.Observable

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