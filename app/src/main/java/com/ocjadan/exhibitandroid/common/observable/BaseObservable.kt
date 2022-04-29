package com.ocjadan.exhibitandroid.common.observable

import java.lang.RuntimeException

abstract class BaseObservable<T> : Observable<T> {
    private val _listenersMap: MutableSet<T> by lazy { mutableSetOf() }

    override fun addListener(listener: T) {
        if (_listenersMap.contains(listener))
            throw ListenerAlreadyExists()
        _listenersMap.add(listener)
    }

    override fun removeListener(listener: T) {
        if (!_listenersMap.contains(listener))
            throw ListenerDoesNotExist()
        _listenersMap.remove(listener)
    }

    override fun getListeners(): Set<T> {
        return _listenersMap.toSet()
    }

    override fun notifyAllListeners(action: (T) -> Unit) {
        for (listener in getListeners())
            action(listener)
    }

    class ListenerAlreadyExists : RuntimeException()
    class ListenerDoesNotExist : RuntimeException()
}
