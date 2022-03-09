package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.networking.StackOverflowApi

class FetchQuestionsEndpointMock(stackOverflowApi: StackOverflowApi) : FetchQuestionsEndpoint(stackOverflowApi) {
    var isGeneralError = false
    var isNetworkError = false
    var fetchQuestionsCount = 0
        private set

    override suspend fun fetchQuestions(): FetchQuestionsEndpointResult {
        fetchQuestionsCount++
        return when {
            isGeneralError -> FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.FAILURE, emptyList())
            isNetworkError -> FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.NETWORK_ERROR, emptyList())
            else -> FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.SUCCESS, QuestionsTestData.questionData())
        }
    }
}