package com.chuys.gshp.shared.util.commons

import android.os.Build
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.marginStart
import com.chuys.gshp.shared.R

class ToolbarHelper(private var activity: AppCompatActivity) {

    private lateinit var toolbar: Toolbar

    fun configToolbarHelpGeneric(
        titleResource: Any,
        navigationButton: Boolean,
        textSizeResource: Int
    ) {

        toolbar = activity.findViewById(R.id.toolbar)
        var titleView: TextView = toolbar.findViewById(R.id.textView_toolbar_title)
        if (textSizeResource != 0) {
            titleView.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                activity.getResources().getDimension(textSizeResource)
            )
        } else {
            titleView.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                activity.getResources().getDimension(R.dimen.common_standard_text_size_10sp)
            )
        }
        when (titleResource) {
            is Int -> titleView.setText(titleResource)
            is String -> titleView.text = titleResource
            else -> titleView.text = ""
        }

        toolbar.setNavigationOnClickListener { activity.finish() }
    }

}