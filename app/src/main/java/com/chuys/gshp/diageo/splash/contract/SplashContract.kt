package com.chuys.gshp.diageo.splash.contract

interface SplashContract{
    interface SplashPresenterContract{
        fun create()
        fun startCountTime()
        fun finish()
    }
    interface SplashViewContract{
        fun endTime()
    }
}