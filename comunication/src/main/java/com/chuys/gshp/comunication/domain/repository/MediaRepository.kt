package com.chuys.gshp.comunication.domain.repository

import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

interface MediaRepository {
    fun getReferenceToDb()
    fun getAllMedia():Single<Resource<List<MediaModel>>>
}