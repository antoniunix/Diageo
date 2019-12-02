package com.chuys.gshp.pdv.data.provider

import com.chuys.gshp.pdv.data.repository.CheckDataRepository
import com.chuys.gshp.pdv.domain.model.CheckModel
import com.chuys.gshp.pdv.domain.providers.CheckProvider
import com.chuys.gshp.pdv.domain.usescases.SaveReportCheckUseCase
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

class CheckDataProvider(
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread
) : CheckProvider {
    private val repository= CheckDataRepository()

    override fun saveReportCheck(): SingleUseCase<CheckModel, Resource<Boolean>> {
        return SaveReportCheckUseCase(repository,jobExecutor,uiThread)
    }

}