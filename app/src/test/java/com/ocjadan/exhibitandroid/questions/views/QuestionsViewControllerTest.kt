package com.ocjadan.exhibitandroid.questions.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class QuestionsViewControllerTest {

    private lateinit var SUT: QuestionsViewControllerImpl
    private lateinit var layoutInflater: LayoutInflater

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var listenerOne: QuestionsViewController.Listener

    @Mock
    lateinit var listenerTwo: QuestionsViewController.Listener

    @Before
    fun setUp() {
        context = mock()
        layoutInflater = LayoutInflaterMock(context)
        SUT = QuestionsViewControllerImpl(layoutInflater, null)
    }

    @Test
    fun addListener_oneListener_singeListenerAdded() {
        oneListener()
        assert(SUT.getListeners().contains(listenerOne))
        assert(SUT.getListeners().count() == 1)
    }

    @Test
    fun addListener_allListeners_allListenersAdded() {
        allListeners()
        assert(SUT.getListeners().containsAll(listOf(listenerOne, listenerTwo)))
        assert(SUT.getListeners().count() == 2)
    }

    @Test
    fun removeListener_oneListener_otherListenerRemains() {
        allListeners()
        removeOneListener()
        assert(SUT.getListeners().contains(listenerTwo))
        assert(SUT.getListeners().count() == 1)
    }

    @Test
    fun removeListener_allListeners_noListenersRemain() {
        allListeners()
        removeAllListeners()
        assert(!SUT.getListeners().containsAll(listOf(listenerOne, listenerTwo)))
        assert(SUT.getListeners().isEmpty())
    }

    @Test
    fun getRootView_viewIsReturned() {
        val view = SUT.getRootView()
        assert(view::class.isInstance(View(context)))
    }

    @Test(expected = RuntimeException::class)
    fun showQuestions() {
        SUT.showQuestions(listOf())
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Region: Helper Methods
    // ------------------------------------------------------------------------------------------------------------------

    private fun oneListener() {
        SUT.addListener(listenerOne)
    }

    private fun allListeners() {
        SUT.addListener(listenerOne)
        SUT.addListener(listenerTwo)
    }

    private fun removeOneListener() {
        SUT.removeListener(listenerOne)
    }

    private fun removeAllListeners() {
        SUT.removeListener(listenerOne)
        SUT.removeListener(listenerTwo)
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Region: Helper Classes
    // ------------------------------------------------------------------------------------------------------------------

    private class LayoutInflaterMock(private val contextMock: Context) : LayoutInflater(contextMock) {
        override fun cloneInContext(p0: Context?): LayoutInflater {
            TODO("Not yet implemented")
        }

        override fun inflate(resource: Int, root: ViewGroup?, attachToRoot: Boolean): View {
            return View(contextMock)
        }
    }
}