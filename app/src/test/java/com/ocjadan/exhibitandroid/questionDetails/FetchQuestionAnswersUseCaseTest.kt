package com.ocjadan.exhibitandroid.questionDetails

import com.ocjadan.benchmarkable.questionDetails.QuestionAnswer
import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import com.ocjadan.exhibitandroid.networking.questions.FetchQuestionAnswersEndpointMock
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
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.KArgumentCaptor
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FetchQuestionAnswersUseCaseTest {

    private lateinit var SUT: FetchQuestionAnswersUseCaseImpl
    private lateinit var answersCaptor: KArgumentCaptor<List<QuestionAnswer>>

    private lateinit var fetchQuestionAnswersEndpointMock: FetchQuestionAnswersEndpointMock

    @Mock
    private lateinit var listenerOne: FetchQuestionAnswersUseCase.Listener

    @Mock
    private lateinit var listenerTwo: FetchQuestionAnswersUseCase.Listener

    @Before
    fun setUp() {
        val compositionRoot = CompositionRoot()
        val dispatcher = compositionRoot.getTestDispatcher()

        fetchQuestionAnswersEndpointMock = compositionRoot.getFetchQuestionAnswersEndpointMock()
        SUT = FetchQuestionAnswersUseCaseImpl(fetchQuestionAnswersEndpointMock, dispatcher)
        answersCaptor = argumentCaptor()

        allListeners()

        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun addListeners_allListenersAdded() {
        assert(SUT.getListeners().containsAll(listOf(listenerOne, listenerTwo)))
    }

    @Test
    fun addListeners_removeAllListeners_noListenersRemain() {
        removeAllListeners()
        assert(SUT.getListeners().isEmpty())
    }

    @Test
    fun addListeners_removeOneListener_otherListenerRemains() {
        removeListenerOne()
        val listeners = SUT.getListeners()
        assert(listeners.contains(listenerTwo))
        assert(listeners.count() == 1)
    }

    @Test
    fun fetchAnswers_successNotifiedWithAnswers() = runTest {
        SUT.fetchQuestionAnswers(0)

        verify(listenerOne).onFetchQuestionAnswersSuccess(answersCaptor.capture())
        verify(listenerTwo).onFetchQuestionAnswersSuccess(answersCaptor.capture())

        val answersOne = answersCaptor.firstValue
        val answersTwo = answersCaptor.secondValue

        assert(answersOne.isNotEmpty())
        assert(answersTwo.isNotEmpty())
    }

    @Test
    fun fetchAnswers_generalError_failureNotified() = runTest {
        generalError()
        SUT.fetchQuestionAnswers(0)

        verify(listenerOne).onFetchQuestionAnswersFailure()
        verify(listenerTwo).onFetchQuestionAnswersFailure()
    }

    @Test
    fun fetchAnswers_networkError_networkErrorNotified() = runTest {
        networkError()
        SUT.fetchQuestionAnswers(0)

        verify(listenerOne).onFetchQuestionAnswersNetworkError()
        verify(listenerTwo).onFetchQuestionAnswersNetworkError()
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Region: Helper Methods
    // ------------------------------------------------------------------------------------------------------------------

    private fun allListeners() {
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

    private fun generalError() {
        fetchQuestionAnswersEndpointMock.isGeneralError = true
    }

    private fun networkError() {
        fetchQuestionAnswersEndpointMock.isNetworkError = true
    }
}