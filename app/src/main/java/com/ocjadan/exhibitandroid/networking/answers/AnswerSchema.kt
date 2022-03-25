package com.ocjadan.exhibitandroid.networking.answers

import com.ocjadan.exhibitandroid.networking.owners.OwnerSchema

data class AnswerSchema(val owner: OwnerSchema, val id: Long, val body: String)
