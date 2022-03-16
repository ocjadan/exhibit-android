package com.ocjadan.exhibitandroid.common

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), IBackPressedListener {
    protected val fragmentComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.fragmentComponentBuilder().build()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initBackPress()
    }

    private fun initBackPress() {
        // Note: if activity overrides onBackPressed then this won't get called.
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val handled = onBackPressed()
                if (!handled) {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        })
    }
}