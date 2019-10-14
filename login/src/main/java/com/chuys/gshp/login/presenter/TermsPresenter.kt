package com.chuys.gshp.login.presenter

import com.chuys.gshp.login.domain.provider.LoginProvider
import io.reactivex.disposables.CompositeDisposable

class TermsPresenter(val view: LoginContract.TermsViewContract, val loginProvider: LoginProvider) :
    LoginContract.TermsPresenterContract {
    private val disposables = CompositeDisposable()
    override fun getTermsData() {
        disposables.add(loginProvider.getTermsUseCase().execute(null).subscribe { it ->
            if (it.isSuccess) {
                view.loadTerms(it.data!!)
            } else {
                view.showError("some problem")
            }
        })
    }

    override fun destroy() {
        disposables.dispose()
    }

}