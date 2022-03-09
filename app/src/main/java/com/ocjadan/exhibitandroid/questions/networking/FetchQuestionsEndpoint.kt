package com.ocjadan.exhibitandroid.questions.networking

import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.squareup.moshi.JsonDataException
import java.io.IOException
import java.lang.RuntimeException
import java.net.UnknownHostException

open class FetchQuestionsEndpoint(private val stackOverflowApi: StackOverflowApi) {

    enum class FetchQuestionsEndpointStatus {
        SUCCESS, FAILURE, NETWORK_ERROR
    }

    data class FetchQuestionsEndpointResult(
        val status: FetchQuestionsEndpointStatus,
        val questions: List<QuestionSchema> = emptyList()
    )

    open suspend fun fetchQuestions(): FetchQuestionsEndpointResult {
        return try {
            val response = stackOverflowApi.getQuestions()
            val body = response.body() ?: throw RuntimeException("Null response body: $response")
            val questions = body.items
            FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.SUCCESS, questions)
        } catch (ex: JsonDataException) {
            FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.FAILURE)
        } catch (ex: UnknownHostException) {
            FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.NETWORK_ERROR)
        } catch (ex: IOException) {
            // Can be thread cancelled
            FetchQuestionsEndpointResult(FetchQuestionsEndpointStatus.FAILURE)
        }
    }
}