package com.ocjadan.exhibitandroid.dependencyinjection

import com.ocjadan.exhibitandroid.networking.StackOverflowApiMock
import com.ocjadan.exhibitandroid.questions.fetchQuestions.FetchQuestionsEndpointMock
import com.ocjadan.exhibitandroid.questions.fetchQuestions.FetchQuestionsUseCaseMock
import com.ocjadan.exhibitandroid.questions.questionsList.IQuestionsListViewController
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListViewControllerMock
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListViewModelMock
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CompositionRoot {
    fun getStackOverflowApiMock(): StackOverflowApiMock {
        return StackOverflowApiMock()
    }

    fun getFetchQuestionsEndpointMock(): FetchQuestionsEndpointMock {
        return FetchQuestionsEndpointMock(getStackOverflowApiMock().mock)
    }

    fun getFetchQuestionsUseCaseMock(): FetchQuestionsUseCaseMock {
        return FetchQuestionsUseCaseMock(getFetchQuestionsEndpointMock())
    }

    fun getQuestionsListViewModelMock(): QuestionsListViewModelMock {
        return QuestionsListViewModelMock(getFetchQuestionsUseCaseMock())
    }

    fun getQuestionsListViewControllerMock(): IQuestionsListViewController {
        return QuestionsListViewControllerMock()
    }
}