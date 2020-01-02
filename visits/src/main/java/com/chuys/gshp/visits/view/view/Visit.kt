package com.chuys.gshp.visits.view.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.chuys.gshp.shared.util.commons.ToolbarHelper
import com.chuys.gshp.visits.R
import com.chuys.gshp.visits.view.data.provider.VisitDataProvider
import com.chuys.gshp.visits.view.domain.model.VisitModel
import com.chuys.gshp.visits.view.domain.provider.VisitProvider
import com.chuys.gshp.visits.view.presenter.Presenter
import com.chuys.gshp.visits.view.presenter.contract.VisitContract
import com.google.android.material.bottomnavigation.BottomNavigationView

class Visit : AppCompatActivity(), VisitContract.VisitViewContractt,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var menuNavigation: BottomNavigationView
    private lateinit var provider: VisitProvider
    private lateinit var presenter: VisitContract.VisitPresenterContract
    private lateinit var listVisitRecyclerView: RecyclerView
    private lateinit var listAdapter: VisitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visit)

        ToolbarHelper(this).configToolbarHelpGeneric(R.string.app_name_visit, false, 0)
        menuNavigation = findViewById(R.id.bottom_navigation)
        menuNavigation.setOnNavigationItemSelectedListener(this)

        provider = VisitDataProvider(
            JobExecutor(),
            UIThread()
        )
        listVisitRecyclerView = findViewById(R.id.list_visit_recycler)
        presenter = Presenter(this, provider)
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllReports()
    }

    override fun loadRecyclerView(visit: List<VisitModel>) {
        listAdapter = VisitAdapter(visit)
        listVisitRecyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@Visit)
            presenter.clickItemAdapter(
                listAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>,
                this@Visit
            )
        }
    }

    override fun showError() {
       Log.e("error","error ")
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        ActivityManager.changeToActivityFromMenuItem(p0, this)
        return true
    }
}
