package com.ocjadan.exhibitandroid.common

import androidx.appcompat.app.AppCompatActivity
import com.ocjadan.exhibitandroid.MainApplication

abstract class BaseActivity : AppCompatActivity() {
    val activityComponent by lazy {
        (application as MainApplication).appComponent.activityComponentBuilder().activity(this).build()
    }
}