package com.ocjadan.exhibitandroid.questions.questionsList.networking

import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.squareup.moshi.JsonDataException
import java.io.IOException
import java.lang.RuntimeException
import java.net.UnknownHostException

open class FetchQuestionsListItemsEndpoint(private val stackOverflowApi: StackOverflowApi) {

    enum class FetchQuestionsListItemsEndpointStatus {
        SUCCESS, FAILURE, NETWORK_ERROR
    }

    data class FetchQuestionsListItemsEndpointResult(
        val status: FetchQuestionsListItemsEndpointStatus,
        val questionsListItems: List<QuestionsListItemSchema> = emptyList()
    )

    open suspend fun fetchQuestionsListItems(): FetchQuestionsListItemsEndpointResult {
        return try {
            val response = stackOverflowApi.getQuestionsListItems()
            val body = response.body() ?: throw RuntimeException("Null response body: $response")
            val items = body.items
            FetchQuestionsListItemsEndpointResult(FetchQuestionsListItemsEndpointStatus.SUCCESS, items)
        } catch (ex: JsonDataException) {
            FetchQuestionsListItemsEndpointResult(FetchQuestionsListItemsEndpointStatus.FAILURE)
        } catch (ex: UnknownHostException) {
            FetchQuestionsListItemsEndpointResult(FetchQuestionsListItemsEndpointStatus.NETWORK_ERROR)
        } catch (ex: IOException) {
            // Can be thread cancelled
            FetchQuestionsListItemsEndpointResult(FetchQuestionsListItemsEndpointStatus.FAILURE)
        }
    }
}