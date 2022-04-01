package com.ocjadan.exhibitandroid.networking.questionDetails

import android.text.Html
import com.ocjadan.benchmarkable.questionDetails.QuestionAnswer
import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.RuntimeException

open class FetchQuestionAnswersEndpoint(
    private val stackOverflowApi: StackOverflowApi,
    private val dispatcher: CoroutineDispatcher
) {
    sealed class Result {
        data class Success(val answers: List<QuestionAnswer>) : Result()
        object Failure : Result()
        object NetworkError : Result()
    }

    open suspend fun fetchQuestionAnswers(id: Long): Result = withContext(dispatcher) {
        return@withContext try {
            val response = stackOverflowApi.getQuestionAnswers(id)
            val responseBody = response.body() ?: throw RuntimeException("Null response body: $response")
            val answers = mapQuestionAnswerSchemasToQuestionAnswers(responseBody.items)
            Result.Success(answers)
        } catch (ex: JsonDataException) {
            Result.Failure
        } catch (ex: IOException) {
            Result.NetworkError
        }
    }

    private fun mapQuestionAnswerSchemasToQuestionAnswers(schemas: List<QuestionAnswerSchema>): List<QuestionAnswer> {
        return schemas
            .filter { it.answer_id != null && it.body != null }
            .map { QuestionAnswer(it.answer_id!!, Html.fromHtml(it.body).toString()) }
    }
}
