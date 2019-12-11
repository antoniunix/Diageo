package com.chuys.gshp.comunication.presenter

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.comunication.domain.downloadmanager.createFile
import com.chuys.gshp.comunication.domain.downloadmanager.initDownLoadRequest
import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.comunication.domain.provider.MediaProvider
import com.chuys.gshp.comunication.presenter.contract.MediaContract
import com.chuys.gshp.comunication.view.MediaAdapter
import com.chuys.gshp.shared.domain.provider.ContextProvider
import io.reactivex.disposables.CompositeDisposable

class Presenter(val view: MediaContract.MediaViewContract, val mediaProvider: MediaProvider,val contextProvider: ContextProvider) :
    MediaContract.MediaPresenterContract {

    private val disposables = CompositeDisposable()

    override fun getAllMedia() {
        disposables.add(mediaProvider.getLisMediaUseCase().execute(null).subscribe { it ->
            if (it.isSuccess) {
                val sortedList=it.data!!.sortedBy { it.mediaType }
                view.loadRecyclerView(sortedList)
            } else {
                view.showError()
            }
        })
    }

    override fun destroy() {
        disposables.dispose()
    }

    override fun clickItemAdapter(
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
        activity: Activity
    ) {
        val listAdapter = adapter as MediaAdapter
        disposables.add(listAdapter.clickEvent.subscribe { it ->
            val file = createFile("FILE.pdf",contextProvider.getContext())
            view.initDownLoadManager(initDownLoadRequest(it.url, it.title, file))

        })
    }


}