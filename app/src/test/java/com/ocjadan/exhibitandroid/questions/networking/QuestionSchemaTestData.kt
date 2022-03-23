package com.ocjadan.exhibitandroid.questions.networking

import com.ocjadan.exhibitandroid.questions.questionsList.networking.QuestionsListItemSchema
import com.ocjadan.exhibitandroid.questions.questionsList.networking.QuestionsListSchema

object QuestionSchemaTestData {
    fun questionListSchema(): QuestionsListSchema {
        return QuestionsListSchema(questionSchemas())
    }

    fun questionSchemas(): List<QuestionsListItemSchema> {
        return listOf(QuestionsListItemSchema(question_id = 0), QuestionsListItemSchema(question_id = 1))
    }
}