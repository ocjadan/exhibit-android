package com.ocjadan.exhibitandroid.common.viewcontroller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class BaseObservableViewController<Listener>(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?,
    @LayoutRes rootViewId: Int
) : IObservableViewController<Listener> {

    private val rootView = layoutInflater.inflate(rootViewId, viewGroup, false)
    private val _listenersMap: MutableSet<Listener> by lazy { mutableSetOf() }

    override val listenersMap: Set<Listener>
        get() = _listenersMap

    override fun getRootView(): View {
        return rootView
    }

    protected fun getContext(): Context {
        return rootView.context
    }

    override fun addListener(listener: Listener) {
        _listenersMap.add(listener)
    }

    override fun removeListener(listener: Listener) {
        _listenersMap.remove(listener)
    }
}