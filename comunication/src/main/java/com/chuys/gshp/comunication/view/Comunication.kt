package com.chuys.gshp.comunication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import com.chuys.gshp.comunication.R
import com.chuys.gshp.comunication.domain.model.MediaModel
import com.chuys.gshp.comunication.presenter.Presenter
import com.chuys.gshp.comunication.presenter.contract.MediaContract
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.shared.util.commons.ToolbarHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class Comunication : AppCompatActivity(), MediaContract.MediaViewContract,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var menuNavigation: BottomNavigationView
    lateinit var presenter: MediaContract.MediaPresenterContract
    lateinit var listMediaRecyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunication)
        ToolbarHelper(this).configToolbarHelpGeneric(R.string.app_name_comunication, false, 0)
        menuNavigation = findViewById(R.id.bottom_navigation)
        menuNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        ActivityManager.changeToActivityFromMenuItem(p0, this)
        return true
    }

    override fun loadRecyclerView(media: List<MediaModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
