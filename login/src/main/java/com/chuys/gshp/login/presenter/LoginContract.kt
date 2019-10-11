package com.chuys.gshp.login.presenter

interface LoginContract {
    interface PresenterContract {
        fun validateUser(user: String): Boolean
        fun validatePass(pass: String): Boolean
        fun login(user: String, pass: String)
    }

    interface ViewContract {
        fun loginSuccess()
        fun showError(message: String)
    }
}