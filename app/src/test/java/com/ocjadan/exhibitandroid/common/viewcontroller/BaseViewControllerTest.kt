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

    private val viewGroup = null
    private val attachToRoot = false

    private lateinit var SUT: BaseViewController
    private lateinit var layoutInflater: LayoutInflater

    @Before
    fun setUp() {
        layoutInflater = CompositionRoot().layoutInflater
        SUT = ViewControllerMock(layoutInflater, viewGroup, LAYOUT_ID)
    }

    @Test
    fun init_getRootView_isInstanceOfInflatedView() {
        val inflatedView = layoutInflater.inflate(LAYOUT_ID, viewGroup, attachToRoot)
        assertTrue(SUT.getRootView()::class.isInstance(inflatedView))
    }

    @Test
    fun init_getContext() {
        (SUT as ViewControllerMock).testContext()
    }

    private class ViewControllerMock(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, @LayoutRes rootViewId: Int) :
        BaseViewController(layoutInflater, viewGroup, rootViewId) {
        fun testContext() = getContext()
    }
}