package com.chuys.gshp.shared.domain.usecase

import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<Params, T>(
    val threadExecutor: ThreadExecutor,
    val postExecutionThread: PostExecutionThread
) : UseCase<Params, Single<T>>() {

    /**
     * Executes the current use case
     *
     * @param params the parameters (optionals) used to execute the use case
     */
    fun execute(params: Params? = null): Single<T> {
        return buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
    }

    /**
     * Executes the current use case
     *
     * @param params the parameters (optionals) used to execute the use case
     * @param observer [SingleSubscriber] which will be listening to the single build
     */
    fun execute(params: Params? = null, observer: DisposableSingleObserver<T>) {
        val observable = buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
        subscribe(observable.subscribeWith(observer))
    }
}