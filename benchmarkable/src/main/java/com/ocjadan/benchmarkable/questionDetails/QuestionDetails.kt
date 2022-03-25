package com.ocjadan.benchmarkable.questionDetails

import java.io.Serializable

data class QuestionDetails(
    val id: Long,
    val isAnswered: Boolean,
    val ownerId: Long,
    val ownerName: String,
    val ownerProfileImage: String
) : Serializable
