package com.ocjadan.benchmarkable.questionDetails

import java.io.Serializable

data class QuestionDetails(
    val id: Int,
    val isAnswered: Boolean,
    val ownerId: Int,
    val ownerName: String,
    val ownerProfileImage: String
) : Serializable
