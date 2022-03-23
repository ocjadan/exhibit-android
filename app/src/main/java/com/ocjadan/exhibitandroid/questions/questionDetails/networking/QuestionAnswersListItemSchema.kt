package com.ocjadan.exhibitandroid.questions.questionDetails.networking

import com.ocjadan.exhibitandroid.users.OwnerSchema
import com.squareup.moshi.JsonClass

/**
 * Should not throw JsonDataException; all parameters have a default or is nullable.
 */
@JsonClass(generateAdapter = true)
data class QuestionAnswersListItemSchema(
    val owner: OwnerSchema? = null,
    val answer_id: Int? = null,
    val body: String? = null
)
