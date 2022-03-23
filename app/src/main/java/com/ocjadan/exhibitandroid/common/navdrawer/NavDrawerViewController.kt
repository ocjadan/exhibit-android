package com.ocjadan.exhibitandroid.common.navdrawer

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.annotation.NavigationRes
import androidx.core.view.GravityCompat
import androidx.core.view.children
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import com.ocjadan.exhibitandroid.R
import com.ocjadan.exhibitandroid.common.viewcontroller.BaseViewController
import java.lang.RuntimeException

class NavDrawerViewController(
    layoutInflater: LayoutInflater,
    fragmentManager: FragmentManager,
    parent: ViewGroup?,
    @NavigationRes private val navigationGraph: Int
) :
    BaseViewController(layoutInflater, parent, R.layout.drawer),
    INavDrawerViewController, NavigationView.OnNavigationItemSelectedListener {

    private val navHostFragment =
        fragmentManager.findFragmentById(R.id.drawer_nav_host_fragment_container_view) as NavHostFragment
    private val navController = navHostFragment.navController
    private val navView: NavigationView = getRootView().findViewById(R.id.drawer_nav_view)
    private val layDrawer: DrawerLayout = getRootView().findViewById(R.id.layDrawer)

    init {
        initNavController()
        initNavView()
        initNavViewMenu()
        onCreate()
    }

    private fun initNavController() {
        navController.setGraph(navigationGraph)
    }

    private fun initNavView() {
        navView.inflateHeaderView(R.layout.drawer_header)
        navView.inflateMenu(R.menu.drawer_menu)
    }

    private fun initNavViewMenu() {
        val menu = navView.menu
        val menuItems = menu.children
        selectMenuItem(menuItems.first())
    }

    private fun onCreate() {
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        selectMenuItem(item)
        navigateToItem(item)
        closeDrawer()
        return true
    }

    private fun selectMenuItem(item: MenuItem) {
        navView.setCheckedItem(item)
    }

    private fun navigateToItem(item: MenuItem) {
        when (item.title) {
            getContext().getString(R.string.menu_questions) -> navController.navigate(R.id.nav_frag_questions)
            getContext().getString(R.string.menu_posts) -> navController.navigate(R.id.nav_frag_posts)
            else -> throw RuntimeException("Unrecognized menu item: $item")
        }
    }

    override fun openDrawer() {
        layDrawer.open()
    }

    override fun closeDrawer() {
        layDrawer.close()
    }

    override fun isDrawerOpen(): Boolean {
        return layDrawer.isDrawerOpen(GravityCompat.START)
    }

    override fun getNavController(): NavController {
        return navController
    }
}