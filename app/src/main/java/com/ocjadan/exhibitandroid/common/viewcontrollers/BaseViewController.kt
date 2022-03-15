package com.ocjadan.exhibitandroid.common.viewcontrollers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class BaseViewController(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, @LayoutRes rootView: Int) :
    IBaseViewController {

    private val rootView: View = layoutInflater.inflate(rootView, viewGroup, false)

    override fun getRootView(): View {
        return rootView
    }

    protected fun getContext(): Context {
        return rootView.context
    }
}
