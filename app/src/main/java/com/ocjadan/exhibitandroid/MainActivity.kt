package com.ocjadan.exhibitandroid

import android.os.Bundle
import androidx.navigation.NavController
import com.ocjadan.exhibitandroid.common.BaseActivity
import com.ocjadan.exhibitandroid.common.navdrawer.INavDrawerViewController
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawerHelper
import com.ocjadan.exhibitandroid.common.screensNavigator.INavControllerWrapper
import com.ocjadan.exhibitandroid.common.viewcontroller.ViewControllerFactory
import javax.inject.Inject

class MainActivity : BaseActivity(), NavDrawerHelper, INavControllerWrapper {

    @Inject
    lateinit var viewControllerFactory: ViewControllerFactory

    private lateinit var navDrawerVC: INavDrawerViewController

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        navDrawerVC = viewControllerFactory.getNavDrawerViewController(null, R.navigation.drawer_graph)
        setContentView(navDrawerVC.getRootView())
    }

    override fun openDrawer() {
        navDrawerVC.openDrawer()
    }

    override fun closeDrawer() {
        navDrawerVC.closeDrawer()
    }

    override fun isDrawerOpen(): Boolean {
        return navDrawerVC.isDrawerOpen()
    }

    override fun getNavController(): NavController {
        return navDrawerVC.getNavController()
    }
}
