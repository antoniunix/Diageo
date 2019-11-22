package com.chuys.gshp.geolocation.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Address
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.chuys.gshp.geolocation.R
import com.chuys.gshp.shared.presenter.GeolocationContract
import com.chuys.gshp.shared.presenter.Presenter
import kotlinx.android.synthetic.main.geolocation_activity.*


class GeolocationActivity : AppCompatActivity(), GeolocationContract.GeolocationViewContract {

    private val LOCATION_PERMISSION_REQUEST_CODE = 1000
    private val LOCATION_ACTIVITY_REQUEST_CODE = 1000
    private lateinit var presenter: GeolocationContract.GeolocationPresenterContract

    override fun showError() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.geolocation_activity)
        presenter = Presenter(this)

        btn_location.setOnClickListener {
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            presenter.getUserLocation(LOCATION_ACTIVITY_REQUEST_CODE)
        }
    }

    @SuppressLint("MissingSuperCall")
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == LOCATION_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                presenter.getUserLocation(LOCATION_ACTIVITY_REQUEST_CODE)
            } else if (resultCode == Activity.RESULT_CANCELED) {
                showEnableLocationDialog()
            }
        }
    }

    private fun showEnableLocationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(resources.getString(R.string.enabled_location))
            .setPositiveButton("Enable") { dialog, which ->
                presenter.getUserLocation(LOCATION_ACTIVITY_REQUEST_CODE)
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()
            .show()
    }

    override fun showLocation(location: Location) {
       Toast.makeText(this,"Location "+location.longitude+" "+location.latitude,Toast.LENGTH_LONG).show()
    }

    override fun setAddres(address: Address) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}