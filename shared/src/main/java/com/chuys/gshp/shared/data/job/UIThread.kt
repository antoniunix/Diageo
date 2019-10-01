package com.chuys.gshp.shared.data.job

import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * MainThread (UI Thread) implementation based on a [Scheduler]
 * which will execute actions on the Android UI thread
 */
class UIThread : PostExecutionThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}