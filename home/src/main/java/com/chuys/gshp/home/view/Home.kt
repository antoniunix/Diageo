package com.chuys.gshp.home.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.chuys.gshp.home.R
import com.chuys.gshp.navigation.Activities
import com.chuys.gshp.navigation.ActivityManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var menuNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        menuNavigation = findViewById(R.id.bottom_navigation)
        menuNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        ActivityManager.changeToActivityFromMenuItem(p0,this)
        return true
    }


}
