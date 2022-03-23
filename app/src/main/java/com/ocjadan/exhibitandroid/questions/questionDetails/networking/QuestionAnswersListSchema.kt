package com.ocjadan.exhibitandroid.questions.questionDetails.networking

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionAnswersListSchema(val items: List<QuestionAnswersListItemSchema>)