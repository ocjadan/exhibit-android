package com.ocjadan.exhibitandroid.questions.networking

object QuestionSchemaTestData {
    fun questionListSchema(): QuestionListSchema {
        return QuestionListSchema(questionSchemas())
    }

    fun questionSchemas(): List<QuestionSchema> {
        return listOf(QuestionSchema(), QuestionSchema())
    }
}