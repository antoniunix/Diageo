package com.chuys.gshp.comunication.data.provider

import com.chuys.gshp.comunication.data.repository.MediaDataRepository
import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.comunication.domain.provider.MediaProvider
import com.chuys.gshp.comunication.domain.usecase.GetListMediaUseCase
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

class MediaDataProvider (
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread
): MediaProvider {
    private val repository = MediaDataRepository()

    override fun getLisMediaUseCase(): SingleUseCase<Any, Resource<List<MediaModel>>> {
        return GetListMediaUseCase(repository,jobExecutor,uiThread)
    }

}