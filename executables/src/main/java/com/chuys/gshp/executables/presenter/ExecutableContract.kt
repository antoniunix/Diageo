package com.chuys.gshp.executables.presenter

import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.executables.domain.model.ExecutableData

interface ExecutableContract {
    interface PresenterContract{
        fun getMeasurementExecutable()
        fun saveReport()
        fun clickAddPhoto(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)
        fun destroy()
    }
    interface ViewContract{
        fun loadRecyclerView(sku: List<ExecutableData>)
        fun callCamera(idExecutable:String)
        fun showError()
    }
}