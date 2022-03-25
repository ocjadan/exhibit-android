package com.ocjadan.exhibitandroid.networking.questionsList

import com.ocjadan.exhibitandroid.networking.owners.OwnerSchema
import com.squareup.moshi.JsonClass

/**
 * Should not throw JsonDataException; all parameters have a default or is nullable.
 */
@JsonClass(generateAdapter = true)
data class QuestionSchema(
    val owner: OwnerSchema? = null,
    val question_id: Long? = null,
    val title: String = "",
    val is_answered: Boolean = false,
    val creation_date: Long = 0L
)