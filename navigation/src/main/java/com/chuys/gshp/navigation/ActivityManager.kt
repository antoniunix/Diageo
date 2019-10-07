package com.chuys.gshp.navigation

import android.app.Activity
import android.content.Intent
import com.chuys.gshp.shared.domain.constant.StringConstant

class ActivityManager {

    companion object {
        fun changeToActivity(activityName: Activities, activity: Activity) {
            when (activityName) {
                Activities.SPLASH -> activity.startActivity(Intent(activity.applicationContext,
                    Class.forName(StringConstant.PACKAGE_NAME+   Activities.SPLASH.activity)))
                Activities.PDV_LIST -> activity.startActivity(Intent(activity.applicationContext,
                    Class.forName(StringConstant.PACKAGE_NAME+Activities.PDV_LIST.activity)))
                Activities.MENU_REPORT -> activity.startActivity(Intent(activity.applicationContext,
                    Class.forName(StringConstant.PACKAGE_NAME+Activities.MENU_REPORT.activity)))
            }
        }
    }
}