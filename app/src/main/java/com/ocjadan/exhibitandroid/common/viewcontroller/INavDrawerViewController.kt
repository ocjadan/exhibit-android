package com.ocjadan.exhibitandroid.common.viewcontroller

interface INavDrawerViewController : IViewController {
    fun openDrawer()
    fun closeDrawer()
    fun isDrawerOpen(): Boolean
}