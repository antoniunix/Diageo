package com.chuys.gshp.poll.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chuys.gshp.poll.R
import com.chuys.gshp.poll.data.provider.RealDBDataProvider
import com.chuys.gshp.poll.domain.model.Form
import com.chuys.gshp.poll.domain.provider.RealDBProvider
import com.chuys.gshp.poll.presenter.PresenterRealDB
import com.chuys.gshp.poll.presenter.contract.RealDBContract
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.chuys.gshp.shared.data.provider.ContextDataProvider
import kotlinx.android.synthetic.main.activity_encuesta.*

class PollActivity : AppCompatActivity(), RealDBContract.RealDBViewContract {

    private lateinit var realDBProvider: RealDBProvider

    private lateinit var presenter: PresenterRealDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta)
        init()
    }

    private fun init() {
        val contextProvider = ContextDataProvider(this)
        realDBProvider = RealDBDataProvider(JobExecutor(), UIThread(), contextProvider)
        presenter = PresenterRealDB(this, realDBProvider)
        presenter.getData()
    }

    override fun show(data: List<Form>) {
        val textDummy = StringBuilder()
        data.forEach {
            textDummy.append(it.name)
            textDummy.append(" - ")
        }
        txt.setText(textDummy)
    }

    override fun showError(error: String) {

    }
}
