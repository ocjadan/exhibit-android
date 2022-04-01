package com.ocjadan.exhibitandroid.questions.questionsList

import com.ocjadan.exhibitandroid.common.TimeProviderMock
import com.ocjadan.exhibitandroid.database.questions.QuestionsCacheMock
import com.ocjadan.exhibitandroid.database.owners.OwnersCacheMock
import com.ocjadan.exhibitandroid.database.updates.UpdatesCacheMock
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
import org.junit.runner.RunWith

import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.KArgumentCaptor
import org.mockito.kotlin.argumentCaptor

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class FetchQuestionsUseCaseTest {

    private lateinit var SUT: FetchQuestionsUseCase
    private lateinit var questionsCaptor: KArgumentCaptor<List<Question>>

    private lateinit var fetchQuestionsEndpointMock: FetchQuestionsEndpointMock
    private lateinit var questionsCacheMock: QuestionsCacheMock
    private lateinit var ownersCacheMock: OwnersCacheMock
    private lateinit var updatesCacheMock: UpdatesCacheMock
    private lateinit var timeProviderMock: TimeProviderMock

    @Mock
    private lateinit var listenerOne: FetchQuestionsUseCase.Listener

    @Mock
    private lateinit var listenerTwo: FetchQuestionsUseCase.Listener

    @Before
    fun setUp() {
        val compositionRoot = CompositionRoot()
        val dispatcherBg = compositionRoot.getTestDispatcher()
        fetchQuestionsEndpointMock = compositionRoot.getFetchQuestionsEndpointMock()
        questionsCacheMock = compositionRoot.getQuestionsCacheMock()
        ownersCacheMock = compositionRoot.getOwnersCacheMock()
        updatesCacheMock = compositionRoot.getUpdatesCacheMock()
        timeProviderMock = compositionRoot.getTimeProviderMock()

        SUT = FetchQuestionsUseCase(
            fetchQuestionsEndpointMock,
            questionsCacheMock,
            ownersCacheMock,
            updatesCacheMock,
            timeProviderMock,
            dispatcherBg
        )
        questionsCaptor = argumentCaptor()

        addListeners()

        Dispatchers.setMain(dispatcherBg)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
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

    // ------------------------------------------------------------------------------------------------------------------
    // Region: Helper Methods
    // ------------------------------------------------------------------------------------------------------------------

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