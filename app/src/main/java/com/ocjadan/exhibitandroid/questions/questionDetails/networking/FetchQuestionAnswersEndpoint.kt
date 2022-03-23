package com.ocjadan.exhibitandroid.questions.questionDetails.networking

import com.ocjadan.exhibitandroid.answers.AnswerSchema
import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.squareup.moshi.JsonDataException
import java.io.IOException
import java.lang.RuntimeException
import java.net.UnknownHostException

class FetchQuestionAnswersEndpoint(private val stackOverflowApi: StackOverflowApi) {

    enum class FetchQuestionAnswersEndpointStatus {
        SUCCESS, FAILURE, NETWORK_ERROR
    }

    data class FetchQuestionAnswersEndpointResult(
        val status: FetchQuestionAnswersEndpointStatus,
        val answers: List<AnswerSchema>? = null
    )

    suspend fun fetchQuestionAnswers(id: Int): FetchQuestionAnswersEndpointResult {
        return try {
            val response = stackOverflowApi.getQuestionAnswersList(id)
            val responseBody =
                response.body() ?: throw RuntimeException("Null response body: $response")
            val answers = responseBody.items
                .filter { it.owner != null && it.answer_id != null && it.body != null }
                .map { AnswerSchema(it.owner!!, it.answer_id!!, it.body!!) }

            FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.SUCCESS, answers)
        } catch (ex: JsonDataException) {
            FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.FAILURE)
        } catch (ex: UnknownHostException) {
            FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.NETWORK_ERROR)
        } catch (ex: IOException) {
            FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.FAILURE)
        }
    }
}
