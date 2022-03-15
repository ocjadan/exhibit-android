package com.ocjadan.exhibitandroid.common.viewcontroller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class BaseObservableViewController<Listener>(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?,
    @LayoutRes rootView: Int
) : IBaseObservableViewController<Listener>, BaseViewController(layoutInflater, viewGroup, rootView) {
    private val _listenersMap: MutableSet<Listener> by lazy { mutableSetOf() }

    override val listenersMap: Set<Listener>
        get() = _listenersMap

    override fun addListener(listener: Listener) {
        _listenersMap.add(listener)
    }

    override fun removeListener(listener: Listener) {
        _listenersMap.remove(listener)
    }
}