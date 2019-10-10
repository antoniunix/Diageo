package com.chuys.gshp.pdv.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.pdv.R
import com.chuys.gshp.pdv.presenter.contract.PdvContract
import com.chuys.gshp.pdv.data.provider.PdvDataProvider
import com.chuys.gshp.pdv.domain.model.PdvData
import com.chuys.gshp.pdv.domain.providers.PdvProvider
import com.chuys.gshp.pdv.presenter.Presenter
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.google.android.material.bottomnavigation.BottomNavigationView

class ListPdv : AppCompatActivity(), PdvContract.PdvViewContract,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var pdvProvider: PdvProvider
    private lateinit var presenter: PdvContract.PdvPresenterContract
    private lateinit var listPdvRecyclerView: RecyclerView
    private var listAdapter: ListPdvAdapter? = null
    private lateinit var menuNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pdv)
        menuNavigation = findViewById(R.id.bottom_navigation)
        menuNavigation.setOnNavigationItemSelectedListener(this)
        pdvProvider = PdvDataProvider(
            JobExecutor(),
            UIThread()
        )
        listPdvRecyclerView = findViewById(R.id.list_pdv_recycler)
        presenter = Presenter(this, pdvProvider)
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllPdv()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        ActivityManager.changeToActivityFromMenuItem(p0, this)
        return true
    }

    override fun loadRecyclerView(pdv: List<PdvData>) {
        listAdapter = ListPdvAdapter(pdv)
        listPdvRecyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@ListPdv)
            presenter.clickItemAdapter(
                listAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>,
                this@ListPdv
            )
        }
    }

    override fun showError() {
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}
