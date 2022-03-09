package com.ocjadan.exhibitandroid.questions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ocjadan.exhibitandroid.networking.StackOverflowApiMock

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuestionsListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Required for LivaData `set`/`postValue`

    private lateinit var SUT: QuestionsListViewModel
    private lateinit var fetchQuestionsUseCaseMock: FetchQuestionsUseCaseMock

    @Before
    fun setUp() {
        val stackOverflowApiMock = StackOverflowApiMock()
        val fetchQuestionsEndpointMock = FetchQuestionsEndpointMock(stackOverflowApiMock.mock)
        fetchQuestionsUseCaseMock = FetchQuestionsUseCaseMock(fetchQuestionsEndpointMock)
        SUT = QuestionsListViewModel(fetchQuestionsUseCaseMock)
    }

    @Test
    fun loadQuestions_success_questionsReturnedAndNoError() = runTest {
        SUT.loadQuestions()
        assert(SUT.questions.isNotEmpty())
        assert(SUT.error == null)
    }

    @Test
    fun loadQuestions_networkError_noQuestionsAndNetworkError() = runTest {
        networkError()
        SUT.loadQuestions()
        assert(SUT.questions.isEmpty())
        assert(SUT.error == QuestionsListViewModel.QuestionsListError.NETWORK)
    }

    @Test
    fun loadQuestions_generalError_noQuestionsAndGeneralError() = runTest {
        generalError()
        SUT.loadQuestions()
        assert(SUT.questions.isEmpty())
        assert(SUT.error == QuestionsListViewModel.QuestionsListError.FAILURE)
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Region: Helper Methods
    // ------------------------------------------------------------------------------------------------------------------

    private fun generalError() {
        fetchQuestionsUseCaseMock.isGeneralError = true
    }

    private fun networkError() {
        fetchQuestionsUseCaseMock.isNetworkError = true
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Region: Helper Classes
    // ------------------------------------------------------------------------------------------------------------------

    private class FetchQuestionsUseCaseMock(private val fetchQuestionsEndpointMock: FetchQuestionsEndpointMock) :
        FetchQuestionsUseCase(fetchQuestionsEndpointMock) {
        var isGeneralError = false
        var isNetworkError = false

        override suspend fun fetchQuestionsAndNotify() {
            fetchQuestionsEndpointMock.isGeneralError = isGeneralError
            fetchQuestionsEndpointMock.isNetworkError = isNetworkError
            super.fetchQuestionsAndNotify()
        }
    }
}