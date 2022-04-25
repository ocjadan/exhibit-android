package com.ocjadan.exhibitandroid.common.screensNavigator

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ocjadan.exhibitandroid.R

class NavigationController(fragmentManager: FragmentManager) {
    private val navigationController: NavController

    init {
        val navHostFragment =
            fragmentManager.findFragmentById(R.id.drawer_nav_host_fragment) as NavHostFragment
        navigationController = navHostFragment.navController
        navigationController.setGraph(R.navigation.drawer_graph)
    }

    fun navigateTo(@IdRes id: Int, args: Bundle?) {
        navigationController.navigate(id, args)
    }

    fun navigateUp() {
        navigationController.navigateUp()
    }
}