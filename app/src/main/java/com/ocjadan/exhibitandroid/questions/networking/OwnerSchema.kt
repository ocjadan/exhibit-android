package com.ocjadan.exhibitandroid.questions.networking

import com.squareup.moshi.JsonClass

/**
 * Note: Should not throw JsonDataException; hence all parameters have a default or is nullable.
 */

@JsonClass(generateAdapter = true)
data class OwnerSchema(val account_id: Int? = null, val user_id: Int? = null)
