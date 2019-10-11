package com.chuys.gshp.executables.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.executables.R
import com.chuys.gshp.executables.data.provider.ExecutableDataProvider
import com.chuys.gshp.executables.domain.model.ExecutableData
import com.chuys.gshp.executables.domain.provider.ExecutableProvider
import com.chuys.gshp.executables.presenter.ExecutableContract
import com.chuys.gshp.executables.presenter.Presenter
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread

class Executable : AppCompatActivity(),ExecutableContract.ViewContract {

    lateinit var executableProvider: ExecutableProvider
    lateinit var presenter: ExecutableContract.PresenterContract
    lateinit var listExecutableRecyclerView: RecyclerView
    private var listAdapter: ExecutableAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_executable)

        executableProvider = ExecutableDataProvider(
            JobExecutor(),
            UIThread()
        )
        listExecutableRecyclerView=findViewById(R.id.list_executable_recycler)
        presenter=Presenter(this,executableProvider)
    }

    override fun onResume() {
        super.onResume()
        presenter.getMeasurementExecutable()
    }

    override fun loadRecyclerView(sku: List<ExecutableData>) {
        listAdapter = ExecutableAdapter(sku)
        listExecutableRecyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@Executable)
            //presenter.clickItemAdapter(listAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>,this@ListPdv)
        }
    }

    override fun showError() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}
