package com.ocjadan.exhibitandroid.common

import androidx.lifecycle.ViewModel
import com.ocjadan.exhibitandroid.common.viewmodel.ViewModelFactory
import com.ocjadan.exhibitandroid.networking.StackOverflowApiMock
import com.ocjadan.exhibitandroid.questions.questionDetails.FetchQuestionAnswersEndpointMock
import com.ocjadan.exhibitandroid.questions.questionDetails.FetchQuestionAnswersUseCaseMock
import com.ocjadan.exhibitandroid.questions.questionsList.items.FetchQuestionsListItemsEndpointMock
import com.ocjadan.exhibitandroid.questions.questionsList.items.FetchQuestionsListItemsUseCaseMock
import com.ocjadan.exhibitandroid.questions.questionDetails.QuestionDetailsViewModel
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException
import javax.inject.Provider

@ExperimentalCoroutinesApi
internal class ViewModelFactoryTest {

    private lateinit var SUT: ViewModelFactory
    private lateinit var questionsListVMProvider: Provider<QuestionsListViewModel>
    private lateinit var questionDetailsVMProvider: Provider<QuestionDetailsViewModel>

    @Before
    fun setUp() {
        questionsListVMProvider = ViewModelProviderMock(ViewModelProviderMock.ViewModelType.QUESTIONS_LIST)
        questionDetailsVMProvider = ViewModelProviderMock(ViewModelProviderMock.ViewModelType.QUESTION_DETAILS)
        SUT = ViewModelFactory(questionsListVMProvider, questionDetailsVMProvider)
    }

    @Test
    fun create_questionsListViewModel_questionsListViewModelReturned() {
        val viewModel = SUT.create(QuestionsListViewModel::class.java)
        val questionsListViewModel = questionsListVMProvider.get()
        assert(viewModel::class.isInstance(questionsListViewModel))
    }

    @Test(expected = RuntimeException::class)
    fun create_genericViewModel_runtimeExceptionThrown() {
        SUT.create(ViewModel::class.java)
    }

    private class ViewModelProviderMock<T>(private val type: ViewModelType) : Provider<T> {
        private val stackOverflowApiMock by lazy { StackOverflowApiMock().mock }

        enum class ViewModelType {
            QUESTIONS_LIST, QUESTION_DETAILS
        }

        @Suppress("UNCHECKED_CAST")
        override fun get(): T {
            return when (type) {
                ViewModelType.QUESTIONS_LIST -> getQuestionsListVM() as T
                ViewModelType.QUESTION_DETAILS -> getQuestionDetailsVM() as T
            }
        }

        private fun getQuestionDetailsVM(): QuestionDetailsViewModel {
            val fetchQuestionAnswersEndpointMock = FetchQuestionAnswersEndpointMock(stackOverflowApiMock)
            val fetchQuestionsAnswersUseCaseMock = FetchQuestionAnswersUseCaseMock(fetchQuestionAnswersEndpointMock)
            return QuestionDetailsViewModel(fetchQuestionsAnswersUseCaseMock)
        }

        private fun getQuestionsListVM(): QuestionsListViewModel {
            val fetchQuestionsListItemsEndpointMock = FetchQuestionsListItemsEndpointMock(stackOverflowApiMock)
            val fetchQuestionsListItemsUseCaseMock = FetchQuestionsListItemsUseCaseMock(fetchQuestionsListItemsEndpointMock)
            return QuestionsListViewModel(fetchQuestionsListItemsUseCaseMock)
        }
    }
}