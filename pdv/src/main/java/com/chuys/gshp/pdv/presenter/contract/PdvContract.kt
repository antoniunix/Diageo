package com.chuys.gshp.pdv.presenter.contract

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.pdv.domain.model.PdvModel

interface PdvContract {
    interface PdvPresenterContract {
        fun getAllPdv()
        fun clickItemAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, activity: Activity)
        fun destroy()
    }

    interface PdvViewContract {
        fun loadRecyclerView(pdv: List<PdvModel>)
        fun showError()
    }
}