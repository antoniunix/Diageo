package com.chuys.gshp.poll.data.provider

import com.chuys.gshp.poll.data.repository.RealDBDataRepository
import com.chuys.gshp.poll.domain.model.Form
import com.chuys.gshp.poll.domain.usecase.RealDBUseCase
import com.chuys.gshp.shared.data.provider.ContextDataProvider
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.usecase.SingleUseCase


class RealDBDataProvider(
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread,
    private val contextDataProvider: ContextDataProvider
) : com.chuys.gshp.poll.domain.provider.RealDBProvider {

    private val dataFirebaseRepository = RealDBDataRepository()

    override fun getData(): SingleUseCase<Any, List<Form>> {
        return RealDBUseCase(dataFirebaseRepository, jobExecutor, uiThread)
    }

}