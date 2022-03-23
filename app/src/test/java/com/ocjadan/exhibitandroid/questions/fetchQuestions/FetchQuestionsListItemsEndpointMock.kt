package com.ocjadan.exhibitandroid.questions.fetchQuestions

import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.ocjadan.exhibitandroid.questions.questionsList.networking.FetchQuestionsListItemsEndpoint
import com.ocjadan.exhibitandroid.questions.networking.QuestionSchemaTestData

class FetchQuestionsListItemsEndpointMock(stackOverflowApi: StackOverflowApi) :
    FetchQuestionsListItemsEndpoint(stackOverflowApi) {
    var isGeneralError = false
    var isNetworkError = false
    var fetchQuestionsCount = 0
        private set

    override suspend fun fetchQuestionsListItems(): FetchQuestionsListItemsEndpointResult {
        fetchQuestionsCount++
        return when {
            isGeneralError -> FetchQuestionsListItemsEndpointResult(FetchQuestionsListItemsEndpointStatus.FAILURE, emptyList())
            isNetworkError -> FetchQuestionsListItemsEndpointResult(
                FetchQuestionsListItemsEndpointStatus.NETWORK_ERROR,
                emptyList()
            )
            else -> FetchQuestionsListItemsEndpointResult(
                FetchQuestionsListItemsEndpointStatus.SUCCESS,
                QuestionSchemaTestData.questionSchemas()
            )
        }
    }
}