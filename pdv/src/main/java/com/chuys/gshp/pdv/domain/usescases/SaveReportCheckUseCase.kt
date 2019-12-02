package com.chuys.gshp.pdv.domain.usescases

import com.chuys.gshp.pdv.domain.model.CheckModel
import com.chuys.gshp.pdv.domain.repository.CheckRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import io.reactivex.Single

class SaveReportCheckUseCase constructor(
    private val checkRepository: CheckRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<CheckModel,Resource<Boolean>>(threadExecutor,
    postExecutionThread
){
    override fun buildUseCase(params: CheckModel?): Single<Resource<Boolean>> {
        return checkRepository.saveCheck(params)
    }

}