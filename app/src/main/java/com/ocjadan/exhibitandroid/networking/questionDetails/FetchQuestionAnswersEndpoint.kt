package com.ocjadan.exhibitandroid.networking.questionDetails

import android.text.Html
import com.ocjadan.benchmarkable.answers.Answer
import com.ocjadan.exhibitandroid.networking.answers.AnswerSchema
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
        val answers: List<Answer>? = null
    )

    suspend fun fetchQuestionAnswers(id: Long): FetchQuestionAnswersEndpointResult {
        return try {
            val response = stackOverflowApi.getQuestionAnswers(id)
            val responseBody = response.body() ?: throw RuntimeException("Null response body: $response")
            val answerSchemas = responseBody.items
                .filter { it.owner != null && it.answer_id != null && it.body != null }
                .map { AnswerSchema(it.owner!!, it.answer_id!!, it.body!!) }
            val answers = mapAnswerSchemasToAnswers(answerSchemas)

            FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.SUCCESS, answers)
        } catch (ex: JsonDataException) {
            FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.FAILURE)
        } catch (ex: UnknownHostException) {
            FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.NETWORK_ERROR)
        } catch (ex: IOException) {
            FetchQuestionAnswersEndpointResult(FetchQuestionAnswersEndpointStatus.FAILURE)
        }
    }

    private fun mapAnswerSchemasToAnswers(schema: List<AnswerSchema>): List<Answer> {
        return schema.map { Answer(it.id, Html.fromHtml(it.body).toString()) }
    }
}
