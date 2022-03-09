package com.ocjadan.exhibitandroid

import androidx.activity.ComponentActivity

open class BaseActivity : ComponentActivity() {
    val activityComponent by lazy {
        (application as MainApplication).appComponent.activityComponentBuilder().build()
    }
}