package com.ocjadan.exhibitandroid.questions.fetchQuestions

import com.ocjadan.exhibitandroid.questions.FetchQuestionsUseCase

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