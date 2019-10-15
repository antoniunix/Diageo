package com.chuys.gshp.comunication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.chuys.gshp.comunication.R
import com.chuys.gshp.navigation.ActivityManager
import com.chuys.gshp.shared.util.commons.ToolbarHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class Comunication : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var menuNavigation: BottomNavigationView

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
}
