package com.chuys.gshp.reportmenu.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.reportmenu.R
import com.chuys.gshp.reportmenu.data.provider.ReportMenuDataProvider
import com.chuys.gshp.reportmenu.domain.model.Modules
import com.chuys.gshp.reportmenu.domain.providers.ReportMenuProvider
import com.chuys.gshp.reportmenu.presenter.Presenter
import com.chuys.gshp.reportmenu.presenter.contract.ReportMenuContract
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread

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
    }

    override fun onResume() {
        super.onResume()
        presenter.getModules()
    }

    override fun loadRecyclerView(modules: List<Modules>) {
        listAdapter = ReportMenuAdapter(modules)
        listModulesRecyclerView.apply {
            adapter = listAdapter
            layoutManager = GridLayoutManager(this@ReportMenu, 2)
            presenter.clickItemAdapter(listAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
        }
    }

    override fun goToReportMenu(modules: Modules) {
        when(modules.codeModule){
            Activities.PRICE_AND_AVAILABILITY -> ActivityManager.changeToActivity(Activities.PRICE_AND_AVAILABILITY, this)
            Activities.EXECUTABLE -> ActivityManager.changeToActivity(Activities.EXECUTABLE, this)
        }


    }

    override fun showError() {
    }
}
