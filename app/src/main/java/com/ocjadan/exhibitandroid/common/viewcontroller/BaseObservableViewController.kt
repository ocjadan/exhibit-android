package com.ocjadan.exhibitandroid.common.viewcontroller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.lang.RuntimeException

abstract class BaseObservableViewController<Listener>(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?,
    @LayoutRes rootViewId: Int
) : ObservableViewController<Listener> {

    private val rootView = layoutInflater.inflate(rootViewId, viewGroup, false)
    private val _listenersMap: MutableSet<Listener> by lazy { mutableSetOf() }

    override fun getRootView(): View {
        return rootView
    }

    protected fun getContext(): Context {
        return rootView.context
    }

    override fun addListener(listener: Listener) {
        if (_listenersMap.contains(listener))
            throw ListenerAlreadyExists()
        _listenersMap.add(listener)
    }

    override fun removeListener(listener: Listener) {
        if (!_listenersMap.contains(listener))
            throw ListenerDoesNotExist()
        _listenersMap.remove(listener)
    }

    override fun getListeners(): Set<Listener> {
        return _listenersMap.toSet()
    }

    override fun notifyAllListeners(action: (Listener) -> Unit) {
        for (listener in getListeners())
            action(listener)
    }

    class ListenerAlreadyExists : RuntimeException()
    class ListenerDoesNotExist : RuntimeException()
}