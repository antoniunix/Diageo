package com.chuys.gshp.pdv.data.provider

import com.chuys.gshp.pdv.data.repository.PdvDataRepository
import com.chuys.gshp.pdv.domain.providers.PdvProvider
import com.chuys.gshp.pdv.domain.usescases.ListPdvUseCase
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor

class PdvDataProvider(
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread
) : PdvProvider {
    private val pdvRepository = PdvDataRepository()

    override fun getListPdvUseCase(): ListPdvUseCase {
        return ListPdvUseCase(pdvRepository, jobExecutor, uiThread)
    }

}