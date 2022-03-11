package com.ocjadan.exhibitandroid.common

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    protected val fragmentComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.fragmentComponentBuilder().build()
    }
}