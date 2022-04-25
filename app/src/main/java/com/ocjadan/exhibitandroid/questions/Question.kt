package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.owners.Owner
import java.io.Serializable

data class Question(
    val id: Long,
    val title: String,
    val owner: Owner,
    val isAnswered: Boolean,
    val creationDate: Long
) : Serializable