package com.chuys.gshp.executables.presenter

import com.chuys.gshp.executables.domain.model.ExecutableData

interface ExecutableContract {
    interface PresenterContract{
        fun getMeasurementExecutable()
        fun saveReport()
        fun destroy()
    }
    interface ViewContract{
        fun loadRecyclerView(sku: List<ExecutableData>)
        fun showError()
    }
}