package com.ocjadan.exhibitandroid.common

open class BaseObservable<T> {
    private val _listenersMap: MutableSet<T> by lazy { mutableSetOf() }
    val listenersMap: Set<T> = _listenersMap

    fun addListener(listener: T) {
        _listenersMap.add(listener)
    }

    fun removeListener(listener: T) {
        _listenersMap.remove(listener)
    }
}
