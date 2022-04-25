package com.ocjadan.exhibitandroid.questions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import java.lang.RuntimeException

@ExperimentalCoroutinesApi
internal class QuestionsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Required for LivaData `set`/`postValue`

    private lateinit var SUT: QuestionsViewModel
    private lateinit var fetchQuestionsUseCaseMock: FetchQuestionsUseCaseMock

    @Before
    fun setUp() {
        val unconfinedDispatcher = UnconfinedTestDispatcher() // Execution order guarantees that are unusual
        Dispatchers.setMain(unconfinedDispatcher) // Required for viewModelScope

        fetchQuestionsUseCaseMock = CompositionRoot().getFetchQuestionsUseCaseMock()
        SUT = QuestionsViewModel(fetchQuestionsUseCaseMock)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
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
        assert(error == QuestionsViewModel.QuestionsListError.NETWORK)
    }

    @Test
    fun loadQuestions_generalError_noQuestionsAndGeneralError() = runTest {
        generalError()
        SUT.loadQuestions()
        val questions = SUT.questions.value ?: throw RuntimeException()
        val error = SUT.error.value
        assert(questions.isEmpty())
        assert(error == QuestionsViewModel.QuestionsListError.FAILURE)
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