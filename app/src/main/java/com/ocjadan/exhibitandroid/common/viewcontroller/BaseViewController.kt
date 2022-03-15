package com.ocjadan.exhibitandroid.common.viewcontroller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseViewController(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, private val rootView: View) :
    IViewController {

    protected fun getContext(): Context {
        return rootView.context
    }

    override fun getRootView(): View {
        return rootView
    }
}
