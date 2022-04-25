package com.ocjadan.exhibitandroid.common.navdrawer

interface NavDrawer {
    fun openDrawer()
    fun closeDrawer()
    fun handledBackPress(): Boolean
}