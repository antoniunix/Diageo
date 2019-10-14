package com.chuys.gshp.login.domain.provider

import com.chuys.gshp.login.domain.model.SessionData
import com.chuys.gshp.login.domain.model.TermsData
import com.chuys.gshp.shared.domain.models.Resource
import com.chuys.gshp.shared.domain.usecase.SingleUseCase

interface LoginProvider {
    fun getLoginUseCase(): SingleUseCase<Any, Resource<SessionData>>
    fun getTermsUseCase(): SingleUseCase<Any, Resource<TermsData>>
}