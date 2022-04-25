package com.ocjadan.exhibitandroid.common.viewcontroller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class BaseViewController(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, @LayoutRes rootViewId: Int) :
    ViewController {

    private val rootView = layoutInflater.inflate(rootViewId, viewGroup, false)

    protected fun getContext(): Context {
        return rootView.context
    }

    override fun getRootView(): View {
        return rootView
    }
}
