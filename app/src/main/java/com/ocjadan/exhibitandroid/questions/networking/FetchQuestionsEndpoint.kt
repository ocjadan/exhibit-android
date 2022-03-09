package com.ocjadan.exhibitandroid.questions.networking

import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.squareup.moshi.JsonDataException
import java.lang.RuntimeException

open class FetchQuestionsEndpoint(private val stackOverflowApi: StackOverflowApi) {

    enum class FetchQuestionsEndpointStatus {
        SUCCESS, FAILURE, NETWORK_ERROR
    }

    data class FetchQuestionsEndpointResult(val status: FetchQuestionsEndpointStatus, val questions: List<QuestionSchema>)

    open suspend fun fetchQuestions(): FetchQuestionsEndpointResult {
        return try {
            val response = stackOverflowApi.getQuestions()
            val body = response.body() ?: throw RuntimeException("general")
            val questions = body.items
            FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.SUCCESS, questions)
        } catch (ex: JsonDataException) {
            FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.FAILURE, emptyList())
        } catch (ex: Exception) {
            when (ex.message) {
                "general" -> FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.FAILURE, emptyList())
                "network" -> FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.NETWORK_ERROR, emptyList())
                else -> throw RuntimeException("Unknown api exception: $ex")
            }
        }
    }
}