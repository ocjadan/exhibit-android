package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.owners.OwnerSchema
import com.ocjadan.exhibitandroid.questions.questionsList.networking.QuestionsListItemSchema
import com.ocjadan.exhibitandroid.questions.questionsList.networking.QuestionsListSchema

object SchemaTestData {
    fun questionListSchema(): QuestionsListSchema {
        return QuestionsListSchema(questionSchema())
    }

    fun questionSchema(): List<QuestionsListItemSchema> {
        return listOf(
            QuestionsListItemSchema(ownerSchema(), 0, "TITLE1", true),
            QuestionsListItemSchema(ownerSchema(), 1, "TITLE2", false)
        )
    }

    private fun ownerSchema(): OwnerSchema {
        return OwnerSchema(0, 0, "PROFILE_IMAGE", "DISPLAY_NAME")
    }
}