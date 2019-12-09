package com.chuys.gshp.comunication.view.domain.provider

import com.chuys.gshp.comunication.view.domain.model.MediaModel
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface MediaProvider {
        fun getLisMediaUseCase():SingleUseCase<Any,Resource<List<MediaModel>>>
}