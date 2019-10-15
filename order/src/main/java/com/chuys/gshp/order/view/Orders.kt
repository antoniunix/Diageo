package com.chuys.gshp.order.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chuys.gshp.order.R
import com.chuys.gshp.shared.util.commons.ToolbarHelper

class Orders : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        ToolbarHelper(this).configToolbarHelpGeneric(R.string.app_name_order, true, 0)
    }
}
