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

import org.mockito.Mockito.mock
import org.mockito.kotlin.KArgumentCaptor
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class FetchQuestionAnswersUseCaseTest {
    companion object {
        private const val QUESTION_ID: Long = 123
    }

    private lateinit var SUT: FetchQuestionAnswersUseCaseImpl
    private lateinit var answersCaptor: KArgumentCaptor<List<QuestionAnswer>>
    private lateinit var fetchQuestionAnswersEndpointMock: FetchQuestionAnswersEndpointMock

    private val listenerOne = mock(FetchQuestionAnswersUseCase.Listener::class.java)
    private val listenerTwo = mock(FetchQuestionAnswersUseCase.Listener::class.java)

    @Before
    fun setUp() {
        val compositionRoot = CompositionRoot()
        val dispatcher = compositionRoot.testDispatcher

        fetchQuestionAnswersEndpointMock = compositionRoot.getFetchQuestionAnswersEndpointMock()
        SUT = FetchQuestionAnswersUseCaseImpl(fetchQuestionAnswersEndpointMock, dispatcher)
        answersCaptor = argumentCaptor()

        addAllListeners()

        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchAnswers_successNotifiedWithAnswers() = runTest {
        SUT.fetchQuestionAnswers(QUESTION_ID)

        verify(listenerOne).onFetchQuestionAnswersSuccess(answersCaptor.capture())
        verify(listenerTwo).onFetchQuestionAnswersSuccess(answersCaptor.capture())

        assert(answersCaptor.firstValue.isNotEmpty())
        assert(answersCaptor.secondValue.isNotEmpty())
    }

    @Test
    fun fetchAnswers_generalError_failureNotified() = runTest {
        generalError()

        SUT.fetchQuestionAnswers(QUESTION_ID)

        verify(listenerOne).onFetchQuestionAnswersFailure()
        verify(listenerTwo).onFetchQuestionAnswersFailure()
    }

    @Test
    fun fetchAnswers_networkError_networkErrorNotified() = runTest {
        networkError()

        SUT.fetchQuestionAnswers(QUESTION_ID)

        verify(listenerOne).onFetchQuestionAnswersNetworkError()
        verify(listenerTwo).onFetchQuestionAnswersNetworkError()
    }

    private fun addAllListeners() {
        SUT.addListener(listenerOne)
        SUT.addListener(listenerTwo)
    }

    private fun generalError() {
        fetchQuestionAnswersEndpointMock.isGeneralError = true
    }

    private fun networkError() {
        fetchQuestionAnswersEndpointMock.isNetworkError = true
    }
}