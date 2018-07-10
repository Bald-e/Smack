package com.example.ycraighead.smack.Controller

import android.app.Application
import com.example.ycraighead.smack.Utils.SharedPrefs

class App : Application() {

    companion object {
        lateinit var prefs: SharedPrefs
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)
        super.onCreate()
    }
}