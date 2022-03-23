package com.ocjadan.exhibitandroid.questions.questionsList.networking

import com.ocjadan.exhibitandroid.users.OwnerSchema
import com.squareup.moshi.JsonClass

/**
 * Should not throw JsonDataException; all parameters have a default or is nullable.
 */
@JsonClass(generateAdapter = true)
data class QuestionsListItemSchema(
    val owner: OwnerSchema? = null,
    val question_id: Int? = null,
    val title: String = "",
    val is_answered: Boolean = false,
)