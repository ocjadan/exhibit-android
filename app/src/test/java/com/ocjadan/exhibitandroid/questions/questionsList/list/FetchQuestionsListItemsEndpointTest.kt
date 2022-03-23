package com.ocjadan.exhibitandroid.questions.questionsList.list

import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import com.ocjadan.exhibitandroid.networking.StackOverflowApiMock
import com.ocjadan.exhibitandroid.questions.questionsList.networking.FetchQuestionsListItemsEndpoint
import com.ocjadan.exhibitandroid.questions.questionsList.networking.FetchQuestionsListItemsEndpoint.FetchQuestionsListItemsEndpointStatus

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class FetchQuestionsListItemsEndpointTest {

    private lateinit var SUT: FetchQuestionsListItemsEndpoint
    private lateinit var stackOverflowApiMock: StackOverflowApiMock

    @Before
    fun setUp() {
        stackOverflowApiMock = CompositionRoot().getStackOverflowApiMock()
        SUT = FetchQuestionsListItemsEndpoint(stackOverflowApiMock.mock)
    }

    @Test
    fun fetchQuestions_success_statusIsSuccessAndQuestionDataReturned() = runTest {
        success()
        val result = SUT.fetchQuestionsListItems()
        assert(result.status == FetchQuestionsListItemsEndpointStatus.SUCCESS)
        assert(result.questionsListItems.isNotEmpty())
    }

    @Test
    fun fetchQuestions_jsonError_statusIsFailureAndNoQuestionDataReturned() = runTest {
        jsonError()
        val result = SUT.fetchQuestionsListItems()
        assert(result.status == FetchQuestionsListItemsEndpointStatus.FAILURE)
        assert(result.questionsListItems.isEmpty())
    }

    @Test(expected = RuntimeException::class) // UnknownHostException stubbed to throw RuntimeException
    fun fetchQuestions_networkError_statusIsNetworkErrorAndNoQuestionDataReturned() = runTest {
        networkError()
        SUT.fetchQuestionsListItems()
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