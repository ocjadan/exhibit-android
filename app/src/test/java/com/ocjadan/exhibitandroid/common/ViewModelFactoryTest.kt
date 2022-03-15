package com.ocjadan.exhibitandroid.common

import androidx.lifecycle.ViewModel
import com.ocjadan.exhibitandroid.common.viewmodel.ViewModelFactory
import com.ocjadan.exhibitandroid.networking.StackOverflowApiMock
import com.ocjadan.exhibitandroid.questions.fetchQuestions.FetchQuestionsEndpointMock
import com.ocjadan.exhibitandroid.questions.fetchQuestions.FetchQuestionsUseCaseMock
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

    @Before
    fun setUp() {
        questionsListVMProvider = ViewModelProviderMock()
        SUT = ViewModelFactory(questionsListVMProvider)
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

    private class ViewModelProviderMock<T> : Provider<T> {
        override fun get(): T {
            val stackOverflowApiMock = StackOverflowApiMock().mock
            val fetchQuestionsEndpointMock = FetchQuestionsEndpointMock(stackOverflowApiMock)
            val fetchQuestionsUseCaseMock = FetchQuestionsUseCaseMock(fetchQuestionsEndpointMock)
            val questionsListVM = QuestionsListViewModel(fetchQuestionsUseCaseMock)

            @Suppress("UNCHECKED_CAST")
            return questionsListVM as T
        }
    }
}