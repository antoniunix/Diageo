package com.chuys.gshp.pdv.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.pdv.R
import com.chuys.gshp.pdv.data.provider.PdvDataProvider
import com.chuys.gshp.pdv.domain.model.PdvModel
import com.chuys.gshp.pdv.domain.providers.PdvProvider
import com.chuys.gshp.pdv.presenter.Presenter
import com.chuys.gshp.pdv.presenter.contract.PdvContract
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.chuys.gshp.shared.util.commons.ToolbarHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_list_pdv.*

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
        ToolbarHelper(this).configToolbarHelpGeneric(R.string.app_name_pdv,false,0)
        listPdvRecyclerView = findViewById(R.id.list_pdv_recycler)
        presenter =Presenter(this, pdvProvider)
        btn_new_pdv.setOnClickListener{
            ActivityManager.changeToActivity(Activities.PDV_ADD,this)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllPdv()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        ActivityManager.changeToActivityFromMenuItem(p0, this)
        return true
    }

    override fun loadRecyclerView(pdv: List<PdvModel>) {
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
