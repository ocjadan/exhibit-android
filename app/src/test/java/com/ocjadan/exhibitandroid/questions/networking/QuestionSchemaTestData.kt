package com.ocjadan.exhibitandroid.questions.networking

object QuestionSchemaTestData {
    fun questionListSchema(): QuestionListSchema {
        return QuestionListSchema(questionSchemas())
    }

    fun questionSchemas(): List<QuestionSchema> {
        return listOf(QuestionSchema(question_id = 0), QuestionSchema(question_id = 1))
    }
}