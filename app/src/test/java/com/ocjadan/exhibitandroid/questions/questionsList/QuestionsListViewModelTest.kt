package com.ocjadan.exhibitandroid.questions.questionsList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ocjadan.exhibitandroid.networking.StackOverflowApiMock
import com.ocjadan.exhibitandroid.questions.fetchQuestions.FetchQuestionsEndpointMock
import com.ocjadan.exhibitandroid.questions.fetchQuestions.FetchQuestionsUseCaseMock

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
internal class QuestionsListViewModelTest {

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
        val questions = SUT.questions.value ?: throw RuntimeException()
        val error = SUT.error.value
        assert(questions.isNotEmpty())
        assert(error == null)
    }

    @Test
    fun loadQuestions_networkError_noQuestionsAndNetworkError() = runTest {
        networkError()
        SUT.loadQuestions()
        val questions = SUT.questions.value ?: throw RuntimeException()
        val error = SUT.error.value
        assert(questions.isEmpty())
        assert(error == QuestionsListViewModel.QuestionsListError.NETWORK)
    }

    @Test
    fun loadQuestions_generalError_noQuestionsAndGeneralError() = runTest {
        generalError()
        SUT.loadQuestions()
        val questions = SUT.questions.value ?: throw RuntimeException()
        val error = SUT.error.value
        assert(questions.isEmpty())
        assert(error == QuestionsListViewModel.QuestionsListError.FAILURE)
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
}