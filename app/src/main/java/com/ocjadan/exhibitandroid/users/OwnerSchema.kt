package com.ocjadan.exhibitandroid.users

import com.squareup.moshi.JsonClass

/**
 * Note: Should throw JsonDataException; hence all parameters have a default or is nullable.
 */
@JsonClass(generateAdapter = true)
data class OwnerSchema(
    val account_id: Int, // Unlike answers, must have an account to post a question.
    val user_id: Int,
    val profile_image: String = "",
    val display_name: String = ""
)
