package com.chuys.gshp.comunication.view.presenter.contract

import com.chuys.gshp.comunication.view.domain.model.MediaModel

interface MediaContract {
        interface  MediaPresenterContract{
            fun getAllMedia()
            fun destroy()
        }

    interface MediaViewContract  {
        fun loadRecyclerView(media :List<MediaModel>)
    }
}