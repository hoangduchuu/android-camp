package com.khtn.androidcamp

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by Huu Hoang on 5/8/19.
 */
class MyApp :Application(){
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }
}