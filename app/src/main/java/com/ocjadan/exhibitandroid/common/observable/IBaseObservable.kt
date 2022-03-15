package com.ocjadan.exhibitandroid.common.observable

interface IBaseObservable<T> {
    val listenersMap: Set<T>
    fun addListener(listener: T)
    fun removeListener(listener: T)
}