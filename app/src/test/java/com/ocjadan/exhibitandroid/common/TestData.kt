package com.ocjadan.exhibitandroid.common

import com.ocjadan.benchmarkable.questionDetails.QuestionAnswer
import com.ocjadan.exhibitandroid.RandomData
import com.ocjadan.exhibitandroid.database.owners.OwnerEntity
import com.ocjadan.exhibitandroid.database.questions.questionWithOwner.QuestionWithOwnerEntity
import com.ocjadan.exhibitandroid.owners.Owner
import com.ocjadan.exhibitandroid.questions.questionsList.Question

object TestData {
    fun getQuestions(amount: Int = 2): List<Question> {
        val result: MutableList<Question> = mutableListOf()
        for (index in 1..amount) {
            result.add(getQuestion())
        }
        return result
    }

    fun getOwners(amount: Int = 2): List<Owner> {
        val result: MutableList<Owner> = mutableListOf()
        for (index in 1..amount) {
            result.add(getOwner())
        }
        return result
    }

    fun getQuestionAnswers(amount: Int = 2): List<QuestionAnswer> {
        val result: MutableList<QuestionAnswer> = mutableListOf()
        for (index in 1..amount) {
            result.add(getQuestionAnswer())
        }
        return result
    }

    fun mapQuestionWithOwnerEntitiesToQuestions(entities: List<QuestionWithOwnerEntity>): List<Question> {
        return entities.map {
            Question(
                it.question.questionId,
                it.question.title,
                mapOwnerEntityToOwner(it.owner),
                it.question.isAnswered,
                it.question.creationDate
            )
        }
    }

    fun mapOwnerEntityToOwner(entity: OwnerEntity): Owner {
        return Owner(entity.accountId, entity.ownerId, entity.image, entity.name)
    }

    private fun getOwner(): Owner {
        val randomLongs = RandomData.getRandomLongs(2)
        val accountId = randomLongs[0]
        val userId = randomLongs[1]
        return Owner(accountId, userId, "PROFILE_IMAGE_$userId", "NAME_$userId")
    }

    private fun getQuestion(): Question {
        val randomLongs = RandomData.getRandomLongs(2)
        val id = randomLongs[0]
        val creationDate = randomLongs[1]
        val isAnswered = id % 2 == 0L
        return Question(id, "TITLE_$id", getOwner(), isAnswered, creationDate)
    }

    private fun getQuestionAnswer(): QuestionAnswer {
        val id = RandomData.getRandomLong()
        return QuestionAnswer(id, "BODY_$id")
    }
}