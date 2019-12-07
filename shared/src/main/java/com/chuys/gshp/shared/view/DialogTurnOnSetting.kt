package com.chuys.gshp.shared.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.DialogFragment

class DialogTurnOnSetting : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Debe activar el GPS")
                .setPositiveButton("Aceptar",
                    DialogInterface.OnClickListener { dialog, id ->
                        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        startActivity(intent)
                    }).setCancelable(false)
            builder.create()

        }?: throw IllegalStateException("Activity cannot be null")
    }

}