package com.chuys.gshp.comunication.view.data.provider

import com.chuys.gshp.comunication.view.domain.provider.MediaProvider
import com.chuys.gshp.shared.domain.executor.PostExecutionThread
import com.chuys.gshp.shared.domain.executor.ThreadExecutor

class MediaDataProvider (
    private val jobExecutor: ThreadExecutor,
    private val uiThread: PostExecutionThread
):MediaProvider{

}