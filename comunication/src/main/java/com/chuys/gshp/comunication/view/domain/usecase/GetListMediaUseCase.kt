package com.chuys.gshp.comunication.view.domain.usecase

import com.chuys.gshp.comunication.view.domain.model.MediaModel
import com.chuys.gshp.comunication.view.domain.repository.MediaRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import io.reactivex.Single

class GetListMediaUseCase constructor(
    private val mediaRepository: MediaRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Any, Resource<List<MediaModel>>>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: Any?): Single<Resource<List<MediaModel>>> {
        return mediaRepository.getAllMedia()
    }
}