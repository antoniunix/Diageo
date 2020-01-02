package com.chuys.gshp.comunication.view

import android.app.DownloadManager
import android.content.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.comunication.R
import com.chuys.gshp.comunication.data.provider.MediaDataProvider
import com.chuys.gshp.comunication.domain.model.ComunicationModel
import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.comunication.domain.provider.MediaProvider
import com.chuys.gshp.comunication.presenter.Presenter
import com.chuys.gshp.comunication.presenter.contract.MediaContract
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.shared.data.job.JobExecutor
import com.chuys.gshp.shared.data.job.UIThread
import com.chuys.gshp.shared.data.provider.ContextDataProvider
import com.chuys.gshp.shared.domain.provider.ContextProvider
import com.chuys.gshp.shared.util.commons.ToolbarHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R.attr.name
import androidx.core.content.FileProvider
import com.chuys.gshp.shared.domain.constant.StringConstant


class Comunication : AppCompatActivity(), MediaContract.MediaViewContract,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var menuNavigation: BottomNavigationView
    private lateinit var provider: MediaProvider
    private lateinit var contextProvider: ContextProvider
    private lateinit var presenter: MediaContract.MediaPresenterContract
    private lateinit var listMediaRecyclerView: RecyclerView
    private var listAdapter: MediaAdapter? = null
    private var downloadID: Long? = 0
    private var nameFileToSave: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunication)
        ToolbarHelper(this).configToolbarHelpGeneric(R.string.app_name_comunication, false, 0)
        menuNavigation = findViewById(R.id.bottom_navigation)
        provider = MediaDataProvider(JobExecutor(), UIThread())
        listMediaRecyclerView = findViewById(R.id.list_media_recycler)
        menuNavigation.setOnNavigationItemSelectedListener(this)
        contextProvider = ContextDataProvider(this)
        presenter = Presenter(this, provider, contextProvider)
        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        ActivityManager.changeToActivityFromMenuItem(p0, this)
        return true
    }

    override fun loadRecyclerView(media: List<ComunicationModel>) {
        listAdapter = MediaAdapter(media)
        listMediaRecyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@Comunication)
            presenter.clickItemAdapter(
                listAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>,
                this@Comunication
            )
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllMedia()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showError() {
        Log.e("error", "error")
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id: Long = intent!!.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (downloadID == id)
                Toast.makeText(this@Comunication, resources.getString(R.string.download_completed), Toast.LENGTH_SHORT).show()
        }
    }

    override fun initDownLoadManager(request: DownloadManager.Request, nameFile: String) {
        val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadID = downloadManager.enqueue(request)
        nameFileToSave = nameFile
    }
}
