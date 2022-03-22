package com.ocjadan.exhibitandroid.common.navdrawer

import com.ocjadan.exhibitandroid.common.viewcontroller.IViewController

interface INavDrawerViewController : IViewController {
    fun openDrawer()
    fun closeDrawer()
    fun isDrawerOpen(): Boolean
}