package com.chuys.gshp.comunication.data.mapper

import com.chuys.gshp.comunication.data.model.MediaData
import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.shared.domain.mapper.Transform

class MediaMapper : Transform<ArrayList<MediaData>, ArrayList<MediaModel>>() {

    override fun transform(value: ArrayList<MediaData>): ArrayList<MediaModel> {
        val media = ArrayList<MediaModel>()
        for (item in value) {
            media.add(getMedia(item))
        }
        return media
    }

    private fun getMedia(value: MediaData): MediaModel {
        return MediaModel(
            value.id,
            value.title,
            value.url,
            value.description,
            value.mediaType
        )
    }
}