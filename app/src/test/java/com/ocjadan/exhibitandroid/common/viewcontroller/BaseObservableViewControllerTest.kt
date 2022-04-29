package com.ocjadan.exhibitandroid.common.viewcontroller

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes

import com.ocjadan.exhibitandroid.common.viewcontroller.BaseObservableViewController.ListenerAlreadyExists
import com.ocjadan.exhibitandroid.common.viewcontroller.BaseObservableViewController.ListenerDoesNotExist
import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot

import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class BaseObservableViewControllerTest {
    interface Listener {
        fun performAction()
    }

    companion object {
        private const val LAYOUT_ID = 123
    }

    private lateinit var SUT: BaseObservableViewController<Listener>
    private lateinit var layoutInflater: LayoutInflater

    private val viewGroup = null
    private val attachToRoot = false
    private val listenerOne = mock(Listener::class.java)
    private val listenerTwo = mock(Listener::class.java)

    @Before
    fun setUp() {
        layoutInflater = CompositionRoot().layoutInflater
        SUT = ObservableViewControllerMock(layoutInflater, null, LAYOUT_ID)
    }

    @Test
    fun init_listenersIsEmpty() {
        assert(SUT.getListeners().isEmpty())
    }

    @Test(expected = ListenerDoesNotExist::class)
    fun init_removeListener_throwsListenerDoesNotExist() {
        SUT.removeListener(listenerOne)
    }

    @Test(expected = ListenerAlreadyExists::class)
    fun init_addListenerTwice_throwsListenerAlreadyExists() {
        SUT.addListener(listenerOne)
        SUT.addListener(listenerOne)
    }

    @Test
    fun init_getRootView_isInstanceOfInflatedView() {
        val inflatedView = layoutInflater.inflate(LAYOUT_ID, viewGroup, attachToRoot)
        Assert.assertTrue(SUT.getRootView()::class.isInstance(inflatedView))
    }

    @Test
    fun init_getContext() {
        (SUT as ObservableViewControllerMock).testContext()
    }

    @Test
    fun addListeners_twoListenersExists() {
        addAllListeners()
        assert(SUT.getListeners().count() == 2)
        assert(SUT.getListeners().containsAll(listOf(listenerOne, listenerTwo)))
    }

    @Test
    fun addListeners_removeOne_otherListenerRemains() {
        addAllListeners()
        SUT.removeListener(listenerOne)
        assert(SUT.getListeners().count() == 1)
        assert(SUT.getListeners().contains(listenerTwo))
    }

    @Test
    fun notifyListeners_allListenersNotifiedOnce() {
        addAllListeners()
        SUT.notifyAllListeners { it.performAction() }
        verify(listenerOne, times(1)).performAction()
        verify(listenerTwo, times(1)).performAction()
    }

    private fun addAllListeners() {
        SUT.addListener(listenerOne)
        SUT.addListener(listenerTwo)
    }

    private class ObservableViewControllerMock(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        @LayoutRes layoutRes: Int
    ) : BaseObservableViewController<Listener>(layoutInflater, viewGroup, layoutRes) {
        fun testContext(): Context = getContext()
    }
}