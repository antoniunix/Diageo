package com.chuys.gshp.navigation

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import com.chuys.gshp.shared.domain.constant.StringConstant

class ActivityManager {

    companion object {
        fun changeToActivity(activityName: Activities, activity: Activity) {
            when (activityName) {
                Activities.SPLASH -> activity.startActivity(
                    Intent(
                        activity.applicationContext,
                        Class.forName(StringConstant.PACKAGE_NAME + Activities.SPLASH.activity)
                    )
                )
                Activities.LOGIN -> activity.startActivity(
                    Intent(
                        activity.applicationContext,
                        Class.forName(StringConstant.PACKAGE_NAME + Activities.LOGIN.activity)
                    )
                )
                Activities.HOME -> activity.startActivity(
                    Intent(
                        activity.applicationContext,
                        Class.forName(StringConstant.PACKAGE_NAME + Activities.HOME.activity)
                    )
                )
                Activities.PDV_LIST -> activity.startActivity(
                    Intent(
                        activity.applicationContext,
                        Class.forName(StringConstant.PACKAGE_NAME + Activities.PDV_LIST.activity)
                    )
                )
                Activities.MENU_REPORT -> activity.startActivity(
                    Intent(
                        activity.applicationContext,
                        Class.forName(StringConstant.PACKAGE_NAME + Activities.MENU_REPORT.activity)
                    )
                )
                Activities.PRICE_AND_AVAILABILITY -> activity.startActivity(
                    Intent(
                        activity.applicationContext,
                        Class.forName(StringConstant.PACKAGE_NAME + Activities.PRICE_AND_AVAILABILITY.activity)
                    )
                )
                Activities.EXECUTABLE -> activity.startActivity(
                    Intent(
                        activity.applicationContext,
                        Class.forName(StringConstant.PACKAGE_NAME + Activities.EXECUTABLE.activity)
                    )
                )
            }
        }

        fun changeToActivityFromMenuItem(menuItem: MenuItem, activity: Activity) {
            when (menuItem.itemId) {
                R.id.action_home -> changeToActivity(Activities.HOME, activity)
                R.id.action_pdv -> changeToActivity(Activities.PDV_LIST, activity)
            }
        }
    }
}