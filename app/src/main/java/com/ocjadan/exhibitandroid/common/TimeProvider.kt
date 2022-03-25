package com.ocjadan.exhibitandroid.common

open class TimeProvider {
    fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis()
    }
}