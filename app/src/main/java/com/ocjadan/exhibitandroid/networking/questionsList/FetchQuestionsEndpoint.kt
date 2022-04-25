package com.ocjadan.exhibitandroid.networking.questionsList

import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.ocjadan.exhibitandroid.owners.Owner
import com.ocjadan.exhibitandroid.networking.owners.OwnerSchema
import com.ocjadan.exhibitandroid.questions.Question

import com.squareup.moshi.JsonDataException

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

import java.io.IOException
import java.lang.RuntimeException

open class FetchQuestionsEndpoint(
    private val stackOverflowApi: StackOverflowApi,
    private val dispatcher: CoroutineDispatcher
) {
    sealed class Result {
        data class Success(val questions: List<Question>) : Result()
        object Failure : Result()
        object NetworkError : Result()
    }

    open suspend fun fetchQuestions(): Result = withContext(dispatcher) {
        return@withContext try {
            val response = stackOverflowApi.getQuestions()
            val body = response.body() ?: throw RuntimeException("Null response body: $response")
            val questions = mapQuestionSchemaToQuestion(body.items)
            Result.Success(questions)
        } catch (ex: JsonDataException) {
            Result.Failure
        } catch (ex: IOException) {
            // Okio: if the request could not be executed due to cancellation, a connectivity problem or timeout.
            Result.NetworkError
        }
    }

    private fun mapQuestionSchemaToQuestion(questions: List<QuestionSchema>): List<Question> {
        val questionsWithId = questions.filter { it.question_id != null && it.owner != null }
        return questionsWithId.map {
            Question(
                it.question_id!!,
                it.title,
                mapOwnerSchemaToOwner(it.owner!!),
                it.is_answered,
                it.creation_date
            )
        }
    }

    private fun mapOwnerSchemaToOwner(schema: OwnerSchema): Owner {
        return Owner(schema.account_id, schema.user_id, schema.profile_image, schema.display_name)
    }
}