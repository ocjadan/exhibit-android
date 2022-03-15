package com.ocjadan.exhibitandroid.common.observable

abstract class BaseObservable<T> : IObservable<T> {
    private val _listenersMap: MutableSet<T> by lazy { mutableSetOf() }
    override val listenersMap: Set<T> = _listenersMap

    override fun addListener(listener: T) {
        _listenersMap.add(listener)
    }

    override fun removeListener(listener: T) {
        _listenersMap.remove(listener)
    }
}
