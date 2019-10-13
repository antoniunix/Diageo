package com.chuys.gshp.login.domain.repository

import com.chuys.gshp.login.domain.model.SessionData
import com.chuys.gshp.login.domain.model.TermsData
import com.chuys.gshp.shared.domain.models.Resource
import io.reactivex.Single

interface LoginRepository {
    fun login(): Single<Resource<SessionData>>
    fun getTerms(): Single<Resource<TermsData>>
}