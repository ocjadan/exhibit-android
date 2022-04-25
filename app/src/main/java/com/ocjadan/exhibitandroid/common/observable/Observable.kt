package com.ocjadan.exhibitandroid.common.observable

interface Observable<T> {
    fun addListener(listener: T)
    fun removeListener(listener: T)
    fun getListeners(): Set<T>
}