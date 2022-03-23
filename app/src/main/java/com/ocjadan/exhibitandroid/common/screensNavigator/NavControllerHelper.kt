package com.ocjadan.exhibitandroid.common.screensNavigator

import android.os.Bundle
import androidx.annotation.IdRes

class NavControllerHelper(private val navControllerWrapper: INavControllerWrapper) {
    fun navigateTo(@IdRes id: Int, args: Bundle?) {
        val navController = navControllerWrapper.getNavController()
        navController.navigate(id, args)
    }
}
