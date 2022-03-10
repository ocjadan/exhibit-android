package com.ocjadan.exhibitandroid.questions

internal class FetchQuestionsUseCaseMock(private val fetchQuestionsEndpointMock: FetchQuestionsEndpointMock) :
    FetchQuestionsUseCase(fetchQuestionsEndpointMock) {
    var isGeneralError = false
    var isNetworkError = false

    override suspend fun fetchQuestionsAndNotify() {
        fetchQuestionsEndpointMock.isGeneralError = isGeneralError
        fetchQuestionsEndpointMock.isNetworkError = isNetworkError
        super.fetchQuestionsAndNotify()
    }
}