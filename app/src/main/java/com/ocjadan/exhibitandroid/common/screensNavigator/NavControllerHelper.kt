package com.ocjadan.exhibitandroid.common.screensNavigator

import android.os.Bundle
import androidx.annotation.IdRes

open class NavControllerHelper(private val navControllerWrapper: INavControllerWrapper) {
    fun navigateTo(@IdRes id: Int, args: Bundle?) {
        navControllerWrapper.getNavController().navigate(id, args)
    }

    fun navigateUp() {
        navControllerWrapper.getNavController().navigateUp()
    }
}
