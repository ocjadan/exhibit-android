package com.ocjadan.exhibitandroid.questions.fetchQuestions

import com.ocjadan.exhibitandroid.questions.questionsList.FetchQuestionsListItemsUseCase

class FetchQuestionsListItemsUseCaseMock(private val fetchQuestionsListItemsEndpointMock: FetchQuestionsListItemsEndpointMock) :
    FetchQuestionsListItemsUseCase(fetchQuestionsListItemsEndpointMock) {
    var isGeneralError = false
    var isNetworkError = false

    override suspend fun fetchQuestionsListItemsAndNotify() {
        fetchQuestionsListItemsEndpointMock.isGeneralError = isGeneralError
        fetchQuestionsListItemsEndpointMock.isNetworkError = isNetworkError
        super.fetchQuestionsListItemsAndNotify()
    }
}