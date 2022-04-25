package com.ocjadan.exhibitandroid.common.viewmodel

import androidx.lifecycle.ViewModel
import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot
import com.ocjadan.exhibitandroid.questionDetails.QuestionDetailsViewModel
import com.ocjadan.exhibitandroid.questions.QuestionsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException
import javax.inject.Provider

@ExperimentalCoroutinesApi
internal class ViewModelFactoryTest {

    private lateinit var SUT: ViewModelFactory
    private lateinit var questionsVMProvider: Provider<QuestionsViewModel>
    private lateinit var questionDetailsVMProvider: Provider<QuestionDetailsViewModel>

    @Before
    fun setUp() {
        questionsVMProvider = ViewModelProviderMock(ViewModelProviderMock.ViewModelType.QUESTIONS_LIST)
        questionDetailsVMProvider = ViewModelProviderMock(ViewModelProviderMock.ViewModelType.QUESTION_DETAILS)
        SUT = ViewModelFactory(questionsVMProvider, questionDetailsVMProvider)
    }

    @Test
    fun create_questionsListViewModel_questionsListViewModelReturned() {
        val viewModel = SUT.create(QuestionsViewModel::class.java)
        val questionsListViewModel = questionsVMProvider.get()
        assert(viewModel::class.isInstance(questionsListViewModel))
    }

    @Test
    fun create_questionDetailsViewModel_questionDetailsViewModelReturned() {
        val viewModel = SUT.create(QuestionDetailsViewModel::class.java)
        val questionDetailsViewModel = questionDetailsVMProvider.get()
        assert(viewModel::class.isInstance(questionDetailsViewModel))
    }

    @Test(expected = RuntimeException::class)
    fun create_genericViewModel_runtimeExceptionThrown() {
        SUT.create(ViewModel::class.java)
    }

    private class ViewModelProviderMock<T>(private val type: ViewModelType) : Provider<T> {
        private val compositionRoot = CompositionRoot()

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
            val fetchQuestionsAnswersUseCaseMock = compositionRoot.getFetchQuestionAnswersUseCaseMock()
            return QuestionDetailsViewModel(fetchQuestionsAnswersUseCaseMock)
        }

        private fun getQuestionsListVM(): QuestionsViewModel {
            val fetchQuestionsListItemsUseCaseMock = compositionRoot.getFetchQuestionsUseCaseMock()
            return QuestionsViewModel(fetchQuestionsListItemsUseCaseMock)
        }
    }
}