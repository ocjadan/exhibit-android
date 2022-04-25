package com.ocjadan.exhibitandroid.common

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), BackPressedListener {
    protected val fragmentComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.fragmentComponentBuilder().build()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupBackPressedDispatcher()
    }

    private fun setupBackPressedDispatcher() {
        // Note: if parent activity overrides onBackPressed then this won't get called.
        requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallback())
    }

    private fun backPressedCallback() = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (!handledBackPress()) {
                isEnabled = false
                requireActivity().onBackPressed()
            }
        }
    }
}