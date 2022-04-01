package com.ocjadan.exhibitandroid.networking.questions

import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.ocjadan.exhibitandroid.networking.questionsList.FetchQuestionsEndpoint
import com.ocjadan.exhibitandroid.common.TestData
import kotlinx.coroutines.CoroutineDispatcher

class FetchQuestionsEndpointMock(stackOverflowApi: StackOverflowApi, dispatcher: CoroutineDispatcher) :
    FetchQuestionsEndpoint(stackOverflowApi, dispatcher) {
    var isGeneralError = false
    var isNetworkError = false
    var fetchQuestionsCount = 0
        private set

    override suspend fun fetchQuestions(): Result {
        fetchQuestionsCount++
        return when {
            isGeneralError -> Result.Failure
            isNetworkError -> Result.NetworkError
            else -> Result.Success(TestData.getQuestions())
        }
    }
}