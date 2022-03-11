package com.ocjadan.exhibitandroid

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    val activityComponent by lazy {
        (application as MainApplication).appComponent.activityComponentBuilder().activity(this).build()
    }
}