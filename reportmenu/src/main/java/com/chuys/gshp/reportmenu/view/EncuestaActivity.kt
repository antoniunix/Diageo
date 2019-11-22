package com.chuys.gshp.reportmenu.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chuys.gshp.reportmenu.R
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.chuys.gshp.shared.data.model.Form
import com.chuys.gshp.shared.data.provider.ContextDataProvider
import com.chuys.gshp.shared.data.provider.RealDBDataProvider
import com.chuys.gshp.shared.domain.provider.RealDBProvider
import com.chuys.gshp.shared.presenter.PresenterRealDB
import com.chuys.gshp.shared.presenter.RealDBContract
import kotlinx.android.synthetic.main.activity_encuesta.*

class EncuestaActivity : AppCompatActivity(), RealDBContract.RealDBViewContract {

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
        val sb = StringBuilder()
        data.forEach {
            sb.append(it.name)
            sb.append(" - ")
        }
        txt.setText(sb)
    }

    override fun showError(error: String) {

    }
}
