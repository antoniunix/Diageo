package com.chuys.gshp.reportmenu.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.reportmenu.R
import com.chuys.gshp.reportmenu.data.provider.ReportMenuDataProvider
import com.chuys.gshp.reportmenu.domain.model.Modules
import com.chuys.gshp.reportmenu.domain.providers.ReportMenuProvider
import com.chuys.gshp.reportmenu.presenter.Presenter
import com.chuys.gshp.reportmenu.presenter.contract.ReportMenuContract
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.chuys.gshp.shared.data.realdb.MainDB
import com.chuys.gshp.shared.domain.constant.Activities

class ReportMenu : AppCompatActivity(), ReportMenuContract.ViewContract {

    private lateinit var reportMenuProvider: ReportMenuProvider
    private lateinit var presenter: ReportMenuContract.PresenterContract
    private lateinit var listModulesRecyclerView: RecyclerView
    private var listAdapter: ReportMenuAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_menu)
        reportMenuProvider = ReportMenuDataProvider(
            JobExecutor(),
            UIThread()
        )
        listModulesRecyclerView = findViewById(R.id.list_modules_recycler)
        presenter = Presenter(this, reportMenuProvider)

        MainDB.initRealDB()
    }

    override fun onResume() {
        super.onResume()
        presenter.getModules()
    }

    override fun loadRecyclerView(modules: List<Modules>) {
        listAdapter = ReportMenuAdapter(modules)
        listModulesRecyclerView.apply {
            adapter = listAdapter
            layoutManager = GridLayoutManager(this@ReportMenu,2)
            presenter.clickItemAdapter(listAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
        }
    }

    override fun goToReportMenu() {
    }

    override fun showError() {
    }
}
