package com.ocjadan.exhibitandroid.networking.questionsList

import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.ocjadan.exhibitandroid.owners.Owner
import com.ocjadan.exhibitandroid.networking.owners.OwnerSchema
import com.ocjadan.exhibitandroid.questions.questionsList.Question
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
        val questions: List<Question> = emptyList()
    )

    open suspend fun fetchQuestions(): FetchQuestionsEndpointResult {
        return try {
            val response = stackOverflowApi.getQuestions()
            val body = response.body() ?: throw RuntimeException("Null response body: $response")
            val questions = mapQuestionSchemaToQuestion(body.items)
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