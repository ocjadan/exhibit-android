package com.ocjadan.exhibitandroid.common

interface IBackPressedListener {
    /**
     * @return true if the listener handled the back press; false otherwise.
     */
    fun onBackPressed(): Boolean
}