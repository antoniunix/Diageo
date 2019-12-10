package com.chuys.gshp.comunication.data.mapper

import com.chuys.gshp.comunication.data.model.MediaTypeData
import com.chuys.gshp.comunication.domain.model.MediaTypeModel
import com.chuys.gshp.shared.domain.mapper.Transform

class MediaTypeMapper : Transform<ArrayList<MediaTypeData>, ArrayList<MediaTypeModel>>() {

    override fun transform(value: ArrayList<MediaTypeData>): ArrayList<MediaTypeModel> {
        val mediaType = ArrayList<MediaTypeModel>()
        for (item in value) {
            mediaType.add(getMediaType(item))
        }
        return mediaType
    }
        private fun getMediaType(value: MediaTypeData):MediaTypeModel{
        return MediaTypeModel(
            value.id,
            value.ext,
            value.name
        )
    }

}