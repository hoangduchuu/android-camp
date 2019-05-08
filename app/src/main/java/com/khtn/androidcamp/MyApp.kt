package com.khtn.androidcamp

import android.app.Application

/**
 * Created by Huu Hoang on 5/8/19.
 */

class MyApp:Application(){
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesHelper.init(applicationContext)
    }
}