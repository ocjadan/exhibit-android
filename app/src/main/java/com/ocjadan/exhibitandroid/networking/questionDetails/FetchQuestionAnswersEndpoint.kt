package com.ocjadan.exhibitandroid.networking.questionDetails

import android.text.Html
import com.ocjadan.benchmarkable.questionDetails.QuestionAnswer
import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.squareup.moshi.JsonDataException
import java.io.IOException
import java.lang.RuntimeException
import java.net.UnknownHostException

open class FetchQuestionAnswersEndpoint(private val stackOverflowApi: StackOverflowApi) {

    enum class FetchQuestionAnswersEndpointStatus {
        SUCCESS, FAILURE, NETWORK_ERROR
    }

    data class FetchQuestionAnswersEndpointResult(
        val status: FetchQuestionAnswersEndpointStatus,
        val answers: List<QuestionAnswer>? = null
    )

    open suspend fun fetchQuestionAnswers(id: Long): FetchQuestionAnswersEndpointResult {
        return try {
            val response = stackOverflowApi.getQuestionAnswers(id)
            val responseBody = response.body() ?: throw RuntimeException("Null response body: $response")
            val answers = mapQuestionAnswerSchemasToQuestionAnswers(responseBody.items)
            FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.SUCCESS, answers)
        } catch (ex: JsonDataException) {
            FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.FAILURE)
        } catch (ex: UnknownHostException) {
            FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.NETWORK_ERROR)
        } catch (ex: IOException) {
            FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.FAILURE)
        }
    }

    private fun mapQuestionAnswerSchemasToQuestionAnswers(schemas: List<QuestionAnswerSchema>): List<QuestionAnswer> {
        return schemas
            .filter { it.answer_id != null && it.body != null }
            .map { QuestionAnswer(it.answer_id!!, Html.fromHtml(it.body).toString()) }
    }
}
