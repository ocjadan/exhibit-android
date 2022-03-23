package com.ocjadan.exhibitandroid.common.navdrawer

import androidx.navigation.NavController
import com.ocjadan.exhibitandroid.common.viewcontroller.IViewController

interface INavDrawerViewController : IViewController {
    fun openDrawer()
    fun closeDrawer()
    fun isDrawerOpen(): Boolean
    fun getNavController(): NavController
}