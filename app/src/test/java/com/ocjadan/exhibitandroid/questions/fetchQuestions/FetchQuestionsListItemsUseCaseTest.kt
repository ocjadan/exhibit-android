package com.ocjadan.exhibitandroid.questions.fetchQuestions

import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import com.ocjadan.exhibitandroid.questions.questionsList.FetchQuestionsListItemsUseCase
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.KArgumentCaptor
import org.mockito.kotlin.argumentCaptor

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class FetchQuestionsListItemsUseCaseTest {

    private lateinit var SUT: FetchQuestionsListItemsUseCase
    private lateinit var questionsCaptor: KArgumentCaptor<List<QuestionsListItem>>
    private lateinit var fetchQuestionsListItemsEndpointMock: FetchQuestionsListItemsEndpointMock

    @Mock
    private lateinit var listenerOne: FetchQuestionsListItemsUseCase.Listener

    @Mock
    private lateinit var listenerTwo: FetchQuestionsListItemsUseCase.Listener

    @Before
    fun setUp() {
        fetchQuestionsListItemsEndpointMock = CompositionRoot().getFetchQuestionsEndpointMock()
        SUT = FetchQuestionsListItemsUseCase(fetchQuestionsListItemsEndpointMock)
        questionsCaptor = argumentCaptor()
        addListeners()
    }

    @Test
    fun addListeners_allListenersAreAdded() {
        assert(SUT.getListeners().contains(listenerOne))
        assert(SUT.getListeners().contains(listenerTwo))
    }

    @Test
    fun addListeners_removeAllListeners_noListenersRemain() {
        removeAllListeners()
        assert(!SUT.getListeners().contains(listenerOne))
        assert(!SUT.getListeners().contains(listenerTwo))
    }

    @Test
    fun addListeners_removeListener_listenerRemovedAndOtherListenerRemains() {
        SUT.removeListener(listenerOne)
        assert(!SUT.getListeners().contains(listenerOne))
        assert(SUT.getListeners().contains(listenerTwo))
    }

    @Test
    fun fetchQuestionsAndNotify_success_allListenersNotifiedSuccessWithData() = runTest {
        SUT.fetchQuestionsListItemsAndNotify()
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
        SUT.fetchQuestionsListItemsAndNotify()
        verify(listenerOne).onFetchQuestionsUseCaseFailure()
        verify(listenerTwo).onFetchQuestionsUseCaseFailure()
    }

    @Test
    fun fetchQuestionsAndNotify_removeOneListener_generalError_otherListenerNotifiedFailure() = runTest {
        SUT.removeListener(listenerOne)
        generalError()
        SUT.fetchQuestionsListItemsAndNotify()
        verify(listenerOne, never()).onFetchQuestionsUseCaseFailure()
        verify(listenerTwo).onFetchQuestionsUseCaseFailure()
    }

    @Test
    fun fetchQuestionsAndNotify_removeAllListeners_generalError_noListenersNotifiedFailure() = runTest {
        removeAllListeners()
        generalError()
        SUT.fetchQuestionsListItemsAndNotify()
        verify(listenerOne, never()).onFetchQuestionsUseCaseFailure()
        verify(listenerTwo, never()).onFetchQuestionsUseCaseFailure()
    }

    @Test
    fun fetchQuestionsAndNotify_networkError_allListenersNotifiedNetworkError() = runTest {
        networkError()
        SUT.fetchQuestionsListItemsAndNotify()
        verify(listenerOne).onFetchQuestionsUseCaseNetworkError()
        verify(listenerTwo).onFetchQuestionsUseCaseNetworkError()
    }

    @Test
    fun fetchQuestionsAndNotify_removeOneListener_networkError_oneListenerNotifiedNetworkError() = runTest {
        SUT.removeListener(listenerOne)
        networkError()
        SUT.fetchQuestionsListItemsAndNotify()
        verify(listenerOne, never()).onFetchQuestionsUseCaseNetworkError()
        verify(listenerTwo).onFetchQuestionsUseCaseNetworkError()
    }

    @Test
    fun fetchQuestionsAndNotify_removeAllListeners_networkError_noListenersNotifiedNetworkError() = runTest {
        removeAllListeners()
        networkError()
        SUT.fetchQuestionsListItemsAndNotify()
        verify(listenerOne, never()).onFetchQuestionsUseCaseNetworkError()
        verify(listenerTwo, never()).onFetchQuestionsUseCaseNetworkError()
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Region: Helper Methods
    // ------------------------------------------------------------------------------------------------------------------

    private fun generalError() {
        fetchQuestionsListItemsEndpointMock.isGeneralError = true
    }

    private fun networkError() {
        fetchQuestionsListItemsEndpointMock.isNetworkError = true
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