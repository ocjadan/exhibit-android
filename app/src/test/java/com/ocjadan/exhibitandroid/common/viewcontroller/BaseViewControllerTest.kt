package com.ocjadan.exhibitandroid.common.viewcontroller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BaseViewControllerTest {
    companion object {
        private const val LAYOUT_ID = 123
    }

    private val viewGroup: ViewGroup? = null
    private val attachToRoot = false

    private lateinit var SUT: SUTMock
    private lateinit var layoutInflater: LayoutInflater

    @Before
    fun setUp() {
        layoutInflater = CompositionRoot().layoutInflater
        SUT = SUTMock(layoutInflater, viewGroup, LAYOUT_ID)
    }

    @Test
    fun onInitialize_getRootView_isInstanceOfInflatedView() {
        val inflatedView = layoutInflater.inflate(LAYOUT_ID, viewGroup, attachToRoot)
        assertTrue(SUT.getRootView()::class.isInstance(inflatedView))
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Region: Helper Classes
    // ------------------------------------------------------------------------------------------------------------------

    private class SUTMock(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, @LayoutRes rootViewId: Int) :
        BaseViewController(layoutInflater, viewGroup, rootViewId)
}