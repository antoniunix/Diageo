package com.chuys.gshp.diageo.activityProvider

import android.app.Activity
import android.content.Intent
import com.chuys.gshp.diageo.splash.view.Splash
import com.chuys.gshp.pdv.view.ListPdv
import com.chuys.gshp.shared.domain.constant.Activities

class ActivityManager() {

    companion object {
        fun changeToActivity(activityName: Activities, activity: Activity) {
            when (activityName) {
                Activities.SPLASH -> activity.startActivity(Intent(activity.applicationContext, Splash::class.java))
                Activities.PDV_LIST -> activity.startActivity(Intent(activity.applicationContext, ListPdv::class.java))
            }
        }
    }
}