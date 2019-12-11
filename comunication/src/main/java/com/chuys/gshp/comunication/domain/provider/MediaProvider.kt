package com.chuys.gshp.comunication.domain.provider

import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface MediaProvider {
        fun getLisMediaUseCase():SingleUseCase<Any,Resource<List<MediaModel>>>
}