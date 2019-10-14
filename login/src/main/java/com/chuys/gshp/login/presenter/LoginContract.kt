package com.chuys.gshp.login.presenter

import com.chuys.gshp.login.domain.model.TermsData

interface LoginContract {
    interface LoginPresenterContract {
        fun validateUser(user: String): Boolean
        fun validatePass(pass: String): Boolean
        fun login(user: String, pass: String)
        fun destroy()
    }

    interface LoginViewContract {
        fun loginSuccess()
        fun showError(message: String)
    }

    interface TermsPresenterContract{
        fun getTermsData()
        fun destroy()

    }

    interface TermsViewContract{
        fun loadTerms(termsData: TermsData)
        fun showError(message: String)

    }
}