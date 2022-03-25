package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.networking.owners.OwnerSchema
import com.ocjadan.exhibitandroid.networking.questionsList.QuestionSchema
import com.ocjadan.exhibitandroid.networking.questionsList.QuestionsListSchema

object SchemaTestData {
    fun questionListSchema(): QuestionsListSchema {
        return QuestionsListSchema(questionSchema())
    }

    fun questionSchema(): List<QuestionSchema> {
        return listOf(
            QuestionSchema(ownerSchema(), 0, "TITLE1", true),
            QuestionSchema(ownerSchema(), 1, "TITLE2", false)
        )
    }

    private fun ownerSchema(): OwnerSchema {
        return OwnerSchema(0, 0, "PROFILE_IMAGE", "DISPLAY_NAME")
    }
}