package com.chuys.gshp.comunication.domain.usecase

import com.chuys.gshp.comunication.domain.model.ComunicationModel
import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.comunication.domain.repository.MediaRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import io.reactivex.Single

class GetListMediaUseCase constructor(
    private val mediaRepository: MediaRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Any, Resource<List<ComunicationModel>>>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: Any?): Single<Resource<List<ComunicationModel>>> {
        return mediaRepository.getAllMedia()
    }
}