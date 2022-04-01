package com.ocjadan.exhibitandroid.networking.questionsList

import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonDataException

/**
 * @param items is required.
 * @throws JsonDataException when items is not found.
 */
@JsonClass(generateAdapter = true)
data class QuestionsListSchema(
    val items: List<QuestionSchema>,
    val has_more: Boolean = false,
    val quota_max: Int = 1,
    val quota_remaining: Int = 0
)