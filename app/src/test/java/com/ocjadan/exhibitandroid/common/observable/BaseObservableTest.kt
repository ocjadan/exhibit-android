package com.ocjadan.exhibitandroid.common.observable

import org.junit.Assert.assertTrue

import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.verify
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

class BaseObservableTest {
    interface Listener {
        fun performAction()
    }

    private lateinit var SUT: Observable<Listener>

    private val listenerOne = mock(Listener::class.java)
    private val listenerTwo = mock(Listener::class.java)

    @Before
    fun setUp() {
        SUT = ObservableMock()
    }

    @Test
    fun init_hasNoListeners() {
        assertTrue(SUT.getListeners().isEmpty())
    }

    @Test(expected = BaseObservable.ListenerDoesNotExist::class)
    fun init_removeListener_throwsListenerDoesNotExist() {
        SUT.removeListener(listenerOne)
    }

    @Test(expected = BaseObservable.ListenerAlreadyExists::class)
    fun addListener_sameListenerTwice_throwsListenerAlreadyExists() {
        SUT.addListener(listenerOne)
        SUT.addListener(listenerOne)
    }

    @Test
    fun addListeners_allListenersExist() {
        addAllListeners()
        assertTrue(SUT.getListeners().containsAll(listOf(listenerOne, listenerTwo)))
    }

    @Test
    fun addListeners_removeAllListeners_noListenersRemain() {
        addAllListeners()
        removeAllListeners()
        assert(SUT.getListeners().isEmpty())
    }

    @Test
    fun addListeners_removeOneListener_otherListenerRemains() {
        addAllListeners()
        removeListenerOne()
        val listeners = SUT.getListeners()
        assert(listeners.contains(listenerTwo))
        assert(listeners.count() == 1)
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

    private fun removeListenerOne() {
        SUT.removeListener(listenerOne)
    }

    private fun removeAllListeners() {
        SUT.removeListener(listenerOne)
        SUT.removeListener(listenerTwo)
    }

    private class ObservableMock<T> : BaseObservable<T>()
}