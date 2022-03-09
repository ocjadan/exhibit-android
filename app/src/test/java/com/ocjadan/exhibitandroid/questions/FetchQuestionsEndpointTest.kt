package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.networking.StackOverflowApiMock
import com.ocjadan.exhibitandroid.questions.FetchQuestionsEndpoint.FetchQuestionsEndpointStatus

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

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
    fun fetchQuestions_generalError_statusIsFailureAndNoQuestionDataReturned() = runTest {
        generalError()
        val result = SUT.fetchQuestions()
        assert(result.status == FetchQuestionsEndpointStatus.FAILURE)
        assert(result.questions.isEmpty())
    }

    @Test
    fun fetchQuestions_networkError_statusIsNetworkErrorAndNoQuestionDataReturned() = runTest {
        networkError()
        val result = SUT.fetchQuestions()
        assert(result.status == FetchQuestionsEndpointStatus.NETWORK_ERROR)
        assert(result.questions.isEmpty())
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Region: Helper Methods
    // ------------------------------------------------------------------------------------------------------------------

    private fun success() {
        stackOverflowApiMock.success()
    }

    private fun generalError() {
        stackOverflowApiMock.generalError()
    }

    private fun networkError() {
        stackOverflowApiMock.networkError()
    }
}