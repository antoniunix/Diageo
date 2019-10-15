package com.chuys.gshp.executables.presenter

import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.executables.domain.provider.ExecutableProvider
import com.chuys.gshp.executables.view.ExecutableAdapter
import io.reactivex.disposables.CompositeDisposable

class Presenter constructor(
    val view: ExecutableContract.ViewContract,
    val provider: ExecutableProvider
) : ExecutableContract.PresenterContract {

    private val disposables = CompositeDisposable()

    override fun getMeasurementExecutable() {
        disposables.add(provider.getExecutableUseCase().execute(null).subscribe { it ->
            if (it.isSuccess) {
                view.loadRecyclerView(it.data!!)
            } else {
                view.showError()
            }
        })
    }

    override fun clickAddPhoto(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        val executableAdapter = adapter as ExecutableAdapter
        disposables.add(executableAdapter.clickEvent.subscribe {
            view.callCamera("id")
        })
    }

    override fun saveReport() {
    }

    override fun destroy() {
        disposables.dispose()
    }

}