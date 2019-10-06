package com.chuys.gshp.geolocation.utility.extentions

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager

fun Context.isGPSEnabled()=(getSystemService(Context.LOCATION_SERVICE) as LocationManager).isProviderEnabled(LocationManager.GPS_PROVIDER)

fun Context.checkLocationcheckLocationPermission():Boolean=this.checkCallingOrSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
