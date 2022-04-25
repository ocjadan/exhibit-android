package com.ocjadan.exhibitandroid.common.navdrawer

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.core.view.children
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.ocjadan.exhibitandroid.R
import com.ocjadan.exhibitandroid.common.screensNavigator.ScreensNavigator
import com.ocjadan.exhibitandroid.common.viewcontroller.BaseViewController
import java.lang.RuntimeException

class NavDrawerViewController(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    private val screensNavigator: ScreensNavigator
) : BaseViewController(layoutInflater, parent, R.layout.drawer), NavDrawer, NavigationView.OnNavigationItemSelectedListener {

    private val navView: NavigationView = getRootView().findViewById(R.id.drawer_nav_view)
    private val layDrawer: DrawerLayout = getRootView().findViewById(R.id.layDrawer)

    init {
        initNavView()
    }

    private fun initNavView() {
        navView.inflateHeaderView(R.layout.drawer_header)
        navView.inflateMenu(R.menu.drawer_menu)
        navView.setNavigationItemSelectedListener(this)
        initNavViewMenu()
    }

    private fun initNavViewMenu() {
        val menu = navView.menu
        val menuItems = menu.children
        selectMenuItem(menuItems.first())
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
            getContext().getString(R.string.menu_questions) -> screensNavigator.toQuestions()
            else -> throw RuntimeException("Unrecognized menu item: $item")
        }
    }

    override fun openDrawer() {
        layDrawer.open()
    }

    override fun closeDrawer() {
        layDrawer.close()
    }

    override fun handledBackPress(): Boolean {
        if (drawerIsOpen()) {
            closeDrawer()
            return true
        }
        return false
    }

    private fun drawerIsOpen(): Boolean {
        return layDrawer.isDrawerOpen(GravityCompat.START)
    }
}