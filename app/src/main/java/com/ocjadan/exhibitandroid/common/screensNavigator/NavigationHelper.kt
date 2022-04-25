package com.ocjadan.exhibitandroid.common.screensNavigator

import android.os.Bundle
import androidx.annotation.IdRes

interface NavigationHelper {
    fun navigateTo(@IdRes id: Int, args: Bundle?)
    fun navigateUp()
}
