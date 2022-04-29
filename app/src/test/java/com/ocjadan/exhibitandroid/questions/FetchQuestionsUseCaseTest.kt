package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import com.ocjadan.exhibitandroid.networking.questions.FetchQuestionsEndpointMock

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.never
import org.mockito.kotlin.KArgumentCaptor
import org.mockito.kotlin.argumentCaptor

@ExperimentalCoroutinesApi
internal class FetchQuestionsUseCaseTest {

    private lateinit var SUT: FetchQuestionsUseCaseImpl
    private lateinit var fetchQuestionsEndpointMock: FetchQuestionsEndpointMock

    private val questionsCaptor: KArgumentCaptor<List<Question>> = argumentCaptor()
    private val listenerOne = mock(FetchQuestionsUseCase.Listener::class.java)
    private val listenerTwo = mock(FetchQuestionsUseCase.Listener::class.java)

    @Before
    fun setUp() {
        val compositionRoot = CompositionRoot()
        val dispatcherBg = compositionRoot.testDispatcher

        fetchQuestionsEndpointMock = compositionRoot.getFetchQuestionsEndpointMock()
        SUT = FetchQuestionsUseCaseImpl(
            fetchQuestionsEndpointMock,
            compositionRoot.getQuestionsCacheMock(),
            compositionRoot.getOwnersCacheMock(),
            compositionRoot.getUpdatesCacheMock(),
            compositionRoot.getTimeProviderMock(),
            dispatcherBg
        )

        addListeners()

        Dispatchers.setMain(dispatcherBg)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchQuestionsAndNotify_success_allListenersNotifiedSuccessWithData() = runTest {
        SUT.fetchQuestionsAndNotify()
        verify(listenerOne).onFetchQuestionsUseCaseSuccess(questionsCaptor.capture())
        verify(listenerTwo).onFetchQuestionsUseCaseSuccess(questionsCaptor.capture())

        val questionsListOne = questionsCaptor.firstValue
        val questionsListTwo = questionsCaptor.secondValue
        assert(questionsListOne.isNotEmpty())
        assert(questionsListTwo.isNotEmpty())
    }

    @Test
    fun fetchQuestionsAndNotify_generalError_allListenersNotifiedFailure() = runTest {
        generalError()
        SUT.fetchQuestionsAndNotify()
        verify(listenerOne).onFetchQuestionsUseCaseFailure()
        verify(listenerTwo).onFetchQuestionsUseCaseFailure()
    }

    @Test
    fun fetchQuestionsAndNotify_removeOneListener_generalError_otherListenerNotifiedFailure() = runTest {
        SUT.removeListener(listenerOne)
        generalError()
        SUT.fetchQuestionsAndNotify()
        verify(listenerOne, never()).onFetchQuestionsUseCaseFailure()
        verify(listenerTwo).onFetchQuestionsUseCaseFailure()
    }

    @Test
    fun fetchQuestionsAndNotify_removeAllListeners_generalError_noListenersNotifiedFailure() = runTest {
        removeAllListeners()
        generalError()
        SUT.fetchQuestionsAndNotify()
        verify(listenerOne, never()).onFetchQuestionsUseCaseFailure()
        verify(listenerTwo, never()).onFetchQuestionsUseCaseFailure()
    }

    @Test
    fun fetchQuestionsAndNotify_networkError_allListenersNotifiedNetworkError() = runTest {
        networkError()
        SUT.fetchQuestionsAndNotify()
        verify(listenerOne).onFetchQuestionsUseCaseNetworkError()
        verify(listenerTwo).onFetchQuestionsUseCaseNetworkError()
    }

    @Test
    fun fetchQuestionsAndNotify_removeOneListener_networkError_oneListenerNotifiedNetworkError() = runTest {
        SUT.removeListener(listenerOne)
        networkError()
        SUT.fetchQuestionsAndNotify()
        verify(listenerOne, never()).onFetchQuestionsUseCaseNetworkError()
        verify(listenerTwo).onFetchQuestionsUseCaseNetworkError()
    }

    @Test
    fun fetchQuestionsAndNotify_removeAllListeners_networkError_noListenersNotifiedNetworkError() = runTest {
        removeAllListeners()
        networkError()
        SUT.fetchQuestionsAndNotify()
        verify(listenerOne, never()).onFetchQuestionsUseCaseNetworkError()
        verify(listenerTwo, never()).onFetchQuestionsUseCaseNetworkError()
    }

    private fun generalError() {
        fetchQuestionsEndpointMock.isGeneralError = true
    }

    private fun networkError() {
        fetchQuestionsEndpointMock.isNetworkError = true
    }

    private fun addListeners() {
        SUT.addListener(listenerOne)
        SUT.addListener(listenerTwo)
    }

    private fun removeAllListeners() {
        SUT.removeListener(listenerOne)
        SUT.removeListener(listenerTwo)
    }
}