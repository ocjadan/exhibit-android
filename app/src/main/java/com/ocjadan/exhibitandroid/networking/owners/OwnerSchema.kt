package com.ocjadan.exhibitandroid.networking.owners

import com.squareup.moshi.JsonClass

/**
 * Note: Can answer a question as a guest so account ID may be null.
 */
@JsonClass(generateAdapter = true)
data class OwnerSchema(
    val account_id: Long? = null,
    val user_id: Long,
    val profile_image: String = "",
    val display_name: String = ""
)
