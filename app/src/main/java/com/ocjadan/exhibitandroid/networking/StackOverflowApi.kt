package com.ocjadan.exhibitandroid.networking

import com.ocjadan.exhibitandroid.networking.questionsList.QuestionsListSchema
import com.ocjadan.exhibitandroid.networking.questionDetails.QuestionAnswersListSchema

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StackOverflowApi {
    @GET("questions?site=stackoverflow")
    suspend fun getQuestions(): Response<QuestionsListSchema>

    // Filter for including the answers body in the response
    @GET("questions/{id}/answers?site=stackoverflow&filter=!nKzQURF6Y5")
    suspend fun getQuestionAnswers(@Path("id") id: Long): Response<QuestionAnswersListSchema>
}
