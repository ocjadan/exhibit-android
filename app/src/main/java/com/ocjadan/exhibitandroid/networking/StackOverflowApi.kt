package com.ocjadan.exhibitandroid.networking

import com.ocjadan.exhibitandroid.questions.questionsList.networking.QuestionsListSchema
import com.ocjadan.exhibitandroid.questions.questionDetails.networking.QuestionAnswersListSchema

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StackOverflowApi {
    @GET("questions?site=stackoverflow")
    suspend fun getQuestionsListItems(): Response<QuestionsListSchema>

    // Filter for including the answers body in the response
    @GET("questions/{id}/answers?site=stackoverflow&filter=!nKzQURF6Y5")
    suspend fun getQuestionAnswersList(@Path("id") id: Int): Response<QuestionAnswersListSchema>
}
