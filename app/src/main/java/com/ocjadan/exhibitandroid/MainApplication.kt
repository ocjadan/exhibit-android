package com.ocjadan.exhibitandroid

import android.app.Application
import com.ocjadan.exhibitandroid.dependencyinjection.app.DaggerAppComponent

class MainApplication : Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }
}