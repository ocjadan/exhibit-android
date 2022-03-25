package com.ocjadan.exhibitandroid.networking.questionDetails

import com.ocjadan.exhibitandroid.networking.owners.OwnerSchema
import com.squareup.moshi.JsonClass

/**
 * Should not throw JsonDataException; all parameters have a default or is nullable.
 */
@JsonClass(generateAdapter = true)
data class QuestionAnswerSchema(
    val owner: OwnerSchema? = null,
    val answer_id: Long? = null,
    val body: String? = null
)
