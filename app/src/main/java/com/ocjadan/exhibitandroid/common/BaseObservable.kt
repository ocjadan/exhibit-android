package com.ocjadan.exhibitandroid.common

interface IBaseObservable<T> {
    val listenersMap: Set<T>
    fun addListener(listener: T)
    fun removeListener(listener: T)
}

abstract class BaseObservable<T> : IBaseObservable<T> {
    private val _listenersMap: MutableSet<T> by lazy { mutableSetOf() }
    override val listenersMap: Set<T> = _listenersMap

    override fun addListener(listener: T) {
        _listenersMap.add(listener)
    }

    override fun removeListener(listener: T) {
        _listenersMap.remove(listener)
    }
}
