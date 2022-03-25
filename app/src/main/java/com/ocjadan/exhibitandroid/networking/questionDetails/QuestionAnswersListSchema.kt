package com.ocjadan.exhibitandroid.networking.questionDetails

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionAnswersListSchema(val items: List<QuestionAnswerSchema>)