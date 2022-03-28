package com.ocjadan.exhibitandroid.networking.questions

import com.ocjadan.exhibitandroid.common.TestData
import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.ocjadan.exhibitandroid.networking.questionDetails.FetchQuestionAnswersEndpoint

class FetchQuestionAnswersEndpointMock(stackOverflowApi: StackOverflowApi) : FetchQuestionAnswersEndpoint(stackOverflowApi) {
    var isGeneralError = false
    var isNetworkError = false
    var fetchQuestionAnswersCount = 0
        private set

    override suspend fun fetchQuestionAnswers(id: Long): FetchQuestionAnswersEndpointResult {
        fetchQuestionAnswersCount++
        return when {
            isGeneralError -> FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.FAILURE)
            isNetworkError -> FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.NETWORK_ERROR)
            else -> FetchQuestionAnswersEndpointResult(
                FetchQuestionAnswersEndpointStatus.SUCCESS,
                TestData.getQuestionAnswers()
            )
        }
    }

}
