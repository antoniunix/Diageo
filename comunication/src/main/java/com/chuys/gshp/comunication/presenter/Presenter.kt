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
import kotlinx.android.synthetic.main.row_media.view.*

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
            val file = createFile(createNameTemp(it),contextProvider.getContext())
            view.initDownLoadManager(initDownLoadRequest(it.url, it.title, file))

        })
    }

    private fun createNameTemp(media:MediaModel):String{
        val part=media.title.split("\\s".toRegex())[0]
        return when (media.mediaType) {
            1 ->  part+".pdf"
            2 ->  part+".jpg"
            3 ->  part+".png"
            4 ->  part+".mp4"
            5 ->  part+".html"
            6 ->  part+".docx"
            else -> "Otros"
        }
    }
}