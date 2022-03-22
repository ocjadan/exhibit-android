package com.ocjadan.exhibitandroid.questions.networking

import com.squareup.moshi.JsonClass

/**
 * Note: `items` is required; JsonDataException is thrown if it's not found.
 */

@JsonClass(generateAdapter = true)
data class QuestionsListSchema(
    val items: List<QuestionsListItemSchema>,
    val has_more: Boolean = false,
    val quota_max: Int = 1,
    val quota_remaining: Int = 0
)