package com.chuys.gshp.executables.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
import com.chuys.gshp.shared.util.commons.ToolbarHelper

class Executable : AppCompatActivity(), ExecutableContract.ViewContract {

    lateinit var executableProvider: ExecutableProvider
    lateinit var presenter: ExecutableContract.PresenterContract
    lateinit var listExecutableRecyclerView: RecyclerView
    private var listAdapter: ExecutableAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_executable)
        ToolbarHelper(this).configToolbarHelpGeneric(R.string.app_name_executable, true, 0)
        executableProvider = ExecutableDataProvider(
            JobExecutor(),
            UIThread()
        )
        listExecutableRecyclerView = findViewById(R.id.list_executable_recycler)
        presenter = Presenter(this, executableProvider)
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
            presenter.clickAddPhoto(listAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
        }
    }

    override fun callCamera(idExecutable: String) {
        val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, 1)
    }

    override fun showError() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}
