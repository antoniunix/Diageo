package com.chuys.gshp.login.domain.usescases

import com.chuys.gshp.login.domain.model.SessionData
import com.chuys.gshp.login.domain.repository.LoginRepository
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase
import io.reactivex.Single

class LoginUseCase constructor(
    private val loginRepository: LoginRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Any, Resource<SessionData>>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: Any?): Single<Resource<SessionData>> {
        return loginRepository.login()
    }
}