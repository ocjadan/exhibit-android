package com.ocjadan.exhibitandroid.answers

import com.ocjadan.exhibitandroid.users.OwnerSchema

data class AnswerSchema(val owner: OwnerSchema, val id: Int, val body: String)
