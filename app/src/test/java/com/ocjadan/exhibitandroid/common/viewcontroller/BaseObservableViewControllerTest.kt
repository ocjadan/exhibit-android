package com.ocjadan.exhibitandroid.common.viewcontroller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import org.junit.Before
import org.junit.Test

class BaseObservableViewControllerTest {
    companion object {
        private const val LAYOUT_ID = 123
    }

    private lateinit var SUT: BaseObservableViewController<Listener>
    private lateinit var layoutInflater: LayoutInflater

    @Before
    fun setUp() {
        layoutInflater = CompositionRoot().layoutInflater
        SUT = SUTMock(layoutInflater, null, LAYOUT_ID)
    }

    @Test
    fun onInitialize_getRootView_isInstanceOfInflatedView() {
        val inflatedView = layoutInflater.inflate(LAYOUT_ID, null, false)
        assert(SUT.getRootView()::class.isInstance(inflatedView))
    }

    @Test
    fun onInitialize_listenersIsEmpty() {
        assert(SUT.getListeners().isEmpty())
    }

    @Test(expected = BaseObservableViewController.ListenerNotFound::class)
    fun onInitialize_removeListener_throwsListenerNotFound() {
        SUT.removeListener(object : Listener {})
    }

    @Test
    fun addTwoListeners_twoListenersExists() {
        val listenerOne = object : Listener {}
        val listenerTwo = object : Listener {}
        SUT.addListener(listenerOne)
        SUT.addListener(listenerTwo)
        assert(SUT.getListeners().count() == 2)
        assert(SUT.getListeners().containsAll(listOf(listenerOne, listenerTwo)))
    }

    @Test
    fun addTwoListeners_removeOne_oneListenerRemainsAndIsCorrect() {
        val listenerOne = object : Listener {}
        val listenerTwo = object : Listener {}
        SUT.addListener(listenerOne)
        SUT.addListener(listenerTwo)
        SUT.removeListener(listenerOne)
        assert(SUT.getListeners().count() == 1)
        assert(SUT.getListeners().contains(listenerTwo))
    }

    private interface Listener

    private class SUTMock(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, @LayoutRes layoutRes: Int) :
        BaseObservableViewController<Listener>(layoutInflater, viewGroup, layoutRes)
}