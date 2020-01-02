package com.chuys.gshp.comunication.presenter.contract

import android.app.Activity
import android.app.DownloadManager
import android.webkit.DownloadListener
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.comunication.domain.model.ComunicationModel
import com.chuys.gshp.comunication.domain.model.MediaModel

interface MediaContract {
        interface  MediaPresenterContract{
            fun getAllMedia()
            fun destroy()
            fun clickItemAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,activity:Activity)
        }

    interface MediaViewContract  {
        fun loadRecyclerView(media :List<ComunicationModel>)
        fun initDownLoadManager(request:DownloadManager.Request, nameFile:String)
        fun showError()

    }
}