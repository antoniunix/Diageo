package com.chuys.gshp.geolocation.presenter

import android.Manifest
import android.app.Activity
import android.content.Context.LOCATION_SERVICE
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.chuys.gshp.geolocation.presenter.contract.GeolocationContract
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import io.reactivex.disposables.CompositeDisposable

class Presenter(val view: GeolocationContract.GeolocationViewContract) :
    GeolocationContract.GeolocationPresenterContract {
    private val disposable = CompositeDisposable()
    private val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    private val GRANTED = PackageManager.PERMISSION_GRANTED
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationManager: LocationManager
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback


    override fun getUserLocation(activity: Activity, activityRequestCode: Int) {
       locationManager = activity.getSystemService(LOCATION_SERVICE) as LocationManager
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
        locationRequest = LocationRequest
            .create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(5000)
            .setFastestInterval(0)
        initLocationCallBack()

        if (!isGooglePlayServicesAvailable(activity)) {
            return
        }

        if (!isLocationEnabled()) {
            promptUserToEnableLocation(activityRequestCode,activity)
            return
        }

        getLastKnownLocation()
    }

    private fun initLocationCallBack() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                fusedLocationProviderClient.removeLocationUpdates(locationCallback)
            }
        }
    }

    private fun isGooglePlayServicesAvailable(activity: Activity): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(activity)
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show()
            }
            return false
        }
        return true
    }

    private fun isLocationEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun requestPermission(activity: Activity, requestCode: Int) {
        val permissions = arrayOf(FINE_LOCATION)
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    private fun promptUserToEnableLocation(requestCode: Int,activity: Activity) {
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)
        LocationServices
            .getSettingsClient(activity)
            .checkLocationSettings(builder.build())
            .addOnSuccessListener { locationSettingsResponse -> getLastKnownLocation() }
            .addOnFailureListener() { e ->
                val status = (e as ApiException).statusCode
                when (status) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val resolvableApiException = e as ResolvableApiException
                        resolvableApiException.startResolutionForResult(activity, requestCode)
                    } catch (exception: IntentSender.SendIntentException){
                        exception.printStackTrace()
                    }
                }
            }

    }

    @SuppressWarnings("MissingPermission")
    private fun getLastKnownLocation(){
        fusedLocationProviderClient.lastLocation.addOnCompleteListener {
            val location = it.result
            if (location == null){
                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
            }else{
                //locationResultListener.getLocation(location)
                view.showLocation(location)
            }
        }
    }

    override fun isPermissionGranted(activity: Activity,requestCode: Int) {
       if(ContextCompat.checkSelfPermission(activity, FINE_LOCATION)!=GRANTED){
           requestPermission(activity,requestCode)
        }
    }
}