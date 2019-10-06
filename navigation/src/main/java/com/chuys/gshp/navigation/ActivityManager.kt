package com.chuys.gshp.navigation

import android.app.Activity
import android.content.Intent

class ActivityManager {

    companion object {
        fun changeToActivity(activityName: Activities, activity: Activity) {
            when (activityName) {
                Activities.SPLASH -> activity.startActivity(Intent(activity.applicationContext,
                    Class.forName("com.chuys.gshp.diageo.splash.view.Splash")))
                Activities.PDV_LIST -> activity.startActivity(Intent(activity.applicationContext,
                    Class.forName("com.chuys.gshp.pdv.view.ListPdv")))
                Activities.MENU_REPORT -> activity.startActivity(Intent(activity.applicationContext,
                    Class.forName("com.chuys.gshp.reportmenu.view.ListPdv")))
            }
        }
    }
}