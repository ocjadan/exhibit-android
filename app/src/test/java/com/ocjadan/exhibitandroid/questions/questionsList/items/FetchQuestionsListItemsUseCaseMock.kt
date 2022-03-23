package com.ocjadan.exhibitandroid.questions.questionsList.items

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