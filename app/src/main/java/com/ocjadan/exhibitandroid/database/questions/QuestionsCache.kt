package com.ocjadan.exhibitandroid.database.questions

import com.ocjadan.exhibitandroid.database.owners.OwnerEntity
import com.ocjadan.exhibitandroid.database.questions.questionWithOwner.QuestionWithOwner
import com.ocjadan.exhibitandroid.owners.Owner
import com.ocjadan.exhibitandroid.questions.questionsList.Question
import java.lang.RuntimeException

open class QuestionsCache(private val questionsDao: QuestionsDao) {
    companion object {
        private const val AMOUNT = 30 // Default amount StackOverflow returns
    }

    fun getQuestionsWithOwners(amount: Int = AMOUNT): List<Question> {
        val entities = questionsDao.getAllWithOwners(amount)
        return mapQuestionEntitiesToQuestions(entities)
    }

    fun saveAll(questions: List<Question>) {
        val entities = mapQuestionsToQuestionEntities(questions)
        questionsDao.insertAll(entities)
    }

    private fun mapQuestionsToQuestionEntities(questions: List<Question>): List<QuestionEntity> {
        return questions.map {
            QuestionEntity(
                it.id,
                it.isAnswered,
                it.title,
                it.creationDate,
                it.owner.userId,
            )
        }
    }

    private fun mapQuestionEntitiesToQuestions(entities: List<QuestionWithOwner>): List<Question> {
        return entities.map {
            val question = it.question
            val owner = it.owner
            Question(
                question.questionId,
                question.title,
                mapOwnerEntityToOwner(owner),
                question.isAnswered,
                question.creationDate,
            )
        }
    }

    private fun mapOwnerEntityToOwner(owner: OwnerEntity): Owner {
        val accountId = owner.accountId ?: throw RuntimeException("Question doesn't have an owner")
        return Owner(accountId, owner.ownerId, owner.image, owner.name)
    }
}
