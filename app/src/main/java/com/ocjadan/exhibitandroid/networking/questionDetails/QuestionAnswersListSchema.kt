package com.ocjadan.exhibitandroid.networking.questionDetails

import com.squareup.moshi.JsonClass

/**
 * @param items Do not change name, StackOverflow api returns as { items: ... }.
 */
@JsonClass(generateAdapter = true)
data class QuestionAnswersListSchema(val items: List<QuestionAnswerSchema>)