package com.ocjadan.exhibitandroid.networking

import com.ocjadan.exhibitandroid.RandomData
import com.ocjadan.exhibitandroid.networking.owners.OwnerSchema
import com.ocjadan.exhibitandroid.networking.questionDetails.QuestionAnswerSchema
import com.ocjadan.exhibitandroid.networking.questionsList.QuestionSchema
import com.ocjadan.exhibitandroid.networking.questionsList.QuestionsListSchema

object SchemaTestData {
    fun getQuestionsListSchema(): QuestionsListSchema {
        return QuestionsListSchema(getQuestionSchemas())
    }

    fun getQuestionAnswerSchemas(amount: Int = 2): List<QuestionAnswerSchema> {
        val result: MutableList<QuestionAnswerSchema> = mutableListOf()
        for (index in 1..amount) {
            result.add(getQuestionAnswerSchema())
        }
        return result
    }

    private fun getQuestionSchemas(amount: Int = 2): List<QuestionSchema> {
        val result: MutableList<QuestionSchema> = mutableListOf()
        for (index in 1..amount) {
            result.add(getQuestionSchema())
        }
        return result
    }

    private fun getOwnerSchema(): OwnerSchema {
        val randomLongs = RandomData.getRandomLongs(2)
        val accountId = randomLongs[0]
        val userId = randomLongs[1]
        return OwnerSchema(accountId, userId, "PROFILE_IMAGE_$userId", "DISPLAY_NAME_$userId")
    }

    private fun getQuestionSchema(): QuestionSchema {
        val randomLongs = RandomData.getRandomLongs(2)
        val questionId = randomLongs[0]
        val creationDate = randomLongs[1]
        val isAnswered = questionId.rem(2) == 0L
        return QuestionSchema(getOwnerSchema(), questionId, "TITLE_$questionId", isAnswered, creationDate)
    }

    private fun getQuestionAnswerSchema(): QuestionAnswerSchema {
        val answerId = RandomData.getRandomLong()
        return QuestionAnswerSchema(getOwnerSchema(), answerId, "BODY_$answerId")
    }
}