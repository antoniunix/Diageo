package com.chuys.gshp.login.data.provider

import com.chuys.gshp.login.data.repository.LoginDataRepository
import com.chuys.gshp.login.domain.model.SessionData
import com.chuys.gshp.login.domain.model.TermsData
import com.chuys.gshp.login.domain.provider.LoginProvider
import com.chuys.gshp.login.domain.usescases.LoginUseCase
import com.chuys.gshp.login.domain.usescases.TermsUseCase
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

class LoginDataProvider(
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread
) : LoginProvider {

    private val loginDataRepository = LoginDataRepository()
    override fun getLoginUseCase(): SingleUseCase<Any, Resource<SessionData>> {
        return LoginUseCase(loginDataRepository, jobExecutor, uiThread)
    }

    override fun getTermsUseCase(): SingleUseCase<Any, Resource<TermsData>> {
        return TermsUseCase(loginDataRepository,jobExecutor,uiThread)
    }
}