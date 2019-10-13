package com.chuys.gshp.geolocation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chuys.gshp.geolocation.R
import com.chuys.gshp.geolocation.presenter.contract.GeolocationContract

class GeolocationActivity : AppCompatActivity(), GeolocationContract.GeolocationViewContract {

    override fun showError() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.geolocation_activity)
    }


}