package com.chuys.gshp.shared.data.provider

import com.chuys.gshp.shared.data.model.Form
import com.chuys.gshp.shared.data.repository.RealDBDataRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.provider.RealDBProvider
import com.chuys.gshp.shared.domain.usecase.RealDBUseCase
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import com.google.firebase.database.DataSnapshot

class RealDBDataProvider(
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread,
    private val contextDataProvider: ContextDataProvider
) : RealDBProvider {

    private val dataFirebaseRepository = RealDBDataRepository()

    override fun getData(): SingleUseCase<Any, List<Form>> {
        return RealDBUseCase(dataFirebaseRepository, jobExecutor, uiThread)
    }

}