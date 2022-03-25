package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.owners.Owner
import com.ocjadan.exhibitandroid.questions.questionsList.Question

object TestData {
    fun getQuestions(): List<Question> {
        return listOf(
            Question(0L, "TITLE1", getOwner(), true, 0L),
            Question(1L, "TITLE2", getOwner(), false, 0L)
        )
    }

    private fun getOwner(): Owner {
        return Owner(null, 0L, "PROFILE_IMAGE", "NAME")
    }
}