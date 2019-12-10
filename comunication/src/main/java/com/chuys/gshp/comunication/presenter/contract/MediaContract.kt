package com.chuys.gshp.comunication.presenter.contract

import com.chuys.gshp.comunication.domain.model.MediaModel

interface MediaContract {
        interface  MediaPresenterContract{
            fun getAllMedia()
            fun destroy()
        }

    interface MediaViewContract  {
        fun loadRecyclerView(media :List<MediaModel>)
    }
}