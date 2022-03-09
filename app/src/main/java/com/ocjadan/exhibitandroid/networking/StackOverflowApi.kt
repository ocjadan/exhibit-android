package com.ocjadan.exhibitandroid.networking

import com.ocjadan.exhibitandroid.questions.networking.QuestionListSchema
import retrofit2.Response
import retrofit2.http.GET

interface StackOverflowApi {
    @GET("questions?site=stackoverflow")
    suspend fun getQuestions(): Response<QuestionListSchema>
}
