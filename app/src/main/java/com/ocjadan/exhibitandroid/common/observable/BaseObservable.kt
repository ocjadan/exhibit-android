package com.ocjadan.exhibitandroid.common.observable

abstract class BaseObservable<T> : Observable<T> {
    private val _listenersMap: MutableSet<T> by lazy { mutableSetOf() }

    override fun addListener(listener: T) {
        _listenersMap.add(listener)
    }

    override fun removeListener(listener: T) {
        _listenersMap.remove(listener)
    }

    override fun getListeners(): Set<T> {
        return _listenersMap.toSet()
    }
}
