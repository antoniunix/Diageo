package com.chuys.gshp.comunication.view.data.provider

import com.chuys.gshp.comunication.view.domain.model.MediaModel
import com.chuys.gshp.comunication.view.domain.provider.MediaProvider
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

class MediaDataProvider (
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread
):MediaProvider{
    override fun getLisMediaUseCase(): SingleUseCase<Any, Resource<List<MediaModel>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}