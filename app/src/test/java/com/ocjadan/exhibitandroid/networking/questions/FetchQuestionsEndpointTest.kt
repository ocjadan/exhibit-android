package com.ocjadan.exhibitandroid.networking.questions

import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import com.ocjadan.exhibitandroid.networking.StackOverflowApiMock
import com.ocjadan.exhibitandroid.networking.questionsList.FetchQuestionsEndpoint
import com.ocjadan.exhibitandroid.networking.questionsList.FetchQuestionsEndpoint.Result

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.junit.MockitoJUnitRunner

import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class FetchQuestionsEndpointTest {

    private lateinit var SUT: FetchQuestionsEndpoint
    private lateinit var stackOverflowApiMock: StackOverflowApiMock

    @Before
    fun setUp() {
        val compositionRoot = CompositionRoot()
        val dispatcher = compositionRoot.getTestDispatcher()
        stackOverflowApiMock = compositionRoot.getStackOverflowApiMock()
        SUT = FetchQuestionsEndpoint(stackOverflowApiMock.mock, dispatcher)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchQuestions_success_statusIsSuccessAndQuestionDataReturned() = runTest {
        success()
        val result = SUT.fetchQuestions()
        assert(result is Result.Success)
        assert((result as Result.Success).questions.isNotEmpty())
    }

    @Test
    fun fetchQuestions_jsonError_statusIsFailureAndNoQuestionDataReturned() = runTest {
        jsonError()
        val result = SUT.fetchQuestions()
        assert(result is Result.Failure)
    }

    @Test(expected = RuntimeException::class) // IOException stubbed to throw RuntimeException
    fun fetchQuestions_networkError_statusIsNetworkErrorAndNoQuestionDataReturned() = runTest {
        networkError()
        SUT.fetchQuestions()
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Region: Helper Methods
    // ------------------------------------------------------------------------------------------------------------------

    private fun success() {
        stackOverflowApiMock.success()
    }

    private fun jsonError() {
        stackOverflowApiMock.jsonError()
    }

    private fun networkError() {
        stackOverflowApiMock.networkError()
    }
}