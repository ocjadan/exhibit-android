package com.ocjadan.exhibitandroid.networking.questions

import com.ocjadan.exhibitandroid.common.TestData
import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.ocjadan.exhibitandroid.networking.questionDetails.FetchQuestionAnswersEndpoint
import kotlinx.coroutines.CoroutineDispatcher

class FetchQuestionAnswersEndpointMock(stackOverflowApi: StackOverflowApi, dispatcher: CoroutineDispatcher) :
    FetchQuestionAnswersEndpoint(stackOverflowApi, dispatcher) {
    var isGeneralError = false
    var isNetworkError = false
    var fetchQuestionAnswersCount = 0
        private set

    override suspend fun fetchQuestionAnswers(id: Long): Result {
        fetchQuestionAnswersCount++
        return when {
            isGeneralError -> Result.Failure
            isNetworkError -> Result.NetworkError
            else -> Result.Success(TestData.getQuestionAnswers())
        }
    }
}
