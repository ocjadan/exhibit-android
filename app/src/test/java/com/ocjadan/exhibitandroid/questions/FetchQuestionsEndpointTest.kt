package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.networking.StackOverflowApiMock
import com.ocjadan.exhibitandroid.questions.networking.FetchQuestionsEndpoint
import com.ocjadan.exhibitandroid.questions.networking.FetchQuestionsEndpoint.FetchQuestionsEndpointStatus

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FetchQuestionsEndpointTest {

    private lateinit var SUT: FetchQuestionsEndpoint
    private lateinit var stackOverflowApiMock: StackOverflowApiMock

    @Before
    fun setUp() {
        stackOverflowApiMock = StackOverflowApiMock()
        SUT = FetchQuestionsEndpoint(stackOverflowApiMock.mock)
    }

    @Test
    fun fetchQuestions_success_statusIsSuccessAndQuestionDataReturned() = runTest {
        success()
        val result = SUT.fetchQuestions()
        assert(result.status == FetchQuestionsEndpointStatus.SUCCESS)
        assert(result.questions.isNotEmpty())
    }

    @Test
    fun fetchQuestions_jsonError_statusIsFailureAndNoQuestionDataReturned() = runTest {
        jsonError()
        val result = SUT.fetchQuestions()
        assert(result.status == FetchQuestionsEndpointStatus.FAILURE)
        assert(result.questions.isEmpty())
    }

    @Test(expected = RuntimeException::class) // UnknownHostException stubbed to throw RuntimeException
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