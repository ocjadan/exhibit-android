package com.ocjadan.exhibitandroid

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.ocjadan.exhibitandroid.common.BaseActivity
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawer
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawerViewController
import com.ocjadan.exhibitandroid.common.screensNavigator.NavigationController
import com.ocjadan.exhibitandroid.common.screensNavigator.NavigationHelper
import com.ocjadan.exhibitandroid.common.viewcontroller.ViewControllerFactory
import javax.inject.Inject

class MainActivity : BaseActivity(), NavDrawer, NavigationHelper {

    @Inject
    lateinit var viewControllerFactory: ViewControllerFactory

    @Inject
    lateinit var fragmentManager: FragmentManager

    private lateinit var navDrawerViewController: NavDrawerViewController
    private lateinit var navigationController: NavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        navDrawerViewController = viewControllerFactory.getNavDrawerViewController(null)
        navigationController = NavigationController(fragmentManager)
        setContentView(navDrawerViewController.getRootView())
    }

    override fun openDrawer() {
        navDrawerViewController.openDrawer()
    }

    override fun closeDrawer() {
        navDrawerViewController.closeDrawer()
    }

    override fun navigateTo(id: Int, args: Bundle?) {
        navigationController.navigateTo(id, args)
    }

    override fun navigateUp() {
        navigationController.navigateUp()
    }

    override fun handledBackPress(): Boolean {
        return navDrawerViewController.handledBackPress()
    }
}
