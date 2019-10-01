package com.chuys.gshp.shared.domain.usecase

import io.reactivex.disposables.Disposable

abstract class UseCase<Params, R : Any> {

    // Need Changes this must be change by io.reactivex.disposables.CompositeDisposable
    private var disposable: Disposable? = null


    protected abstract fun buildUseCase(params: Params? = null): R

    /**
     * Dispose from current subscription
     */
    fun dispose() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    /**
     * Sets the current subscription
     *
     * @param subscription
     */
    protected fun subscribe(subscription: Disposable? = null) {
        disposable = subscription
    }
}