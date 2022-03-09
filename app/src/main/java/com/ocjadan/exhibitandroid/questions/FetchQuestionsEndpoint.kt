package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import java.lang.RuntimeException

open class FetchQuestionsEndpoint(private val stackOverflowApi: StackOverflowApi) {

    enum class FetchQuestionsEndpointStatus {
        SUCCESS, FAILURE, NETWORK_ERROR
    }

    data class FetchQuestionsEndpointResult(val status: FetchQuestionsEndpointStatus, val questions: List<QuestionData>)

    open suspend fun fetchQuestions(): FetchQuestionsEndpointResult {
        return try {
            val result = stackOverflowApi.getQuestions()
            FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.SUCCESS, result)
        } catch (ex: Exception) {
            when (ex.message) {
                "general" -> FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.FAILURE, emptyList())
                "network" -> FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.NETWORK_ERROR, emptyList())
                else -> throw RuntimeException("Unknown api error: $ex")
            }
        }
    }
}