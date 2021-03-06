package com.ocjadan.exhibitandroid.database.questions

import com.ocjadan.exhibitandroid.database.owners.OwnerEntity
import com.ocjadan.exhibitandroid.database.questions.questionWithOwner.QuestionWithOwnerEntity
import com.ocjadan.exhibitandroid.owners.Owner
import com.ocjadan.exhibitandroid.questions.Question

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

import java.lang.RuntimeException

open class QuestionsCache(
    private val questionsDao: QuestionsDao,
    private val dispatcher: CoroutineDispatcher
) {
    companion object {
        private const val AMOUNT = 30 // Default amount StackOverflow returns
    }

    open suspend fun getQuestions(amount: Int = AMOUNT): List<Question> = withContext(dispatcher) {
        val entities = questionsDao.getAllWithOwners(amount)
        return@withContext mapQuestionEntitiesToQuestions(entities)
    }


    open suspend fun saveAll(questions: List<Question>) = withContext(dispatcher) {
        val entities = mapQuestionsToQuestionEntities(questions)
        questionsDao.upsertAll(entities)
    }


    private fun mapQuestionsToQuestionEntities(questions: List<Question>): List<QuestionEntity> = questions.map {
        QuestionEntity(it.id, it.isAnswered, it.title, it.creationDate, it.owner.userId)
    }

    private fun mapQuestionEntitiesToQuestions(entities: List<QuestionWithOwnerEntity>): List<Question> = entities.map {
        val question = it.question
        val owner = it.owner
        Question(question.questionId, question.title, mapOwnerEntityToOwner(owner), question.isAnswered, question.creationDate)
    }

    private fun mapOwnerEntityToOwner(owner: OwnerEntity): Owner {
        val accountId = owner.accountId ?: throw RuntimeException("Question doesn't have an owner")
        return Owner(accountId, owner.ownerId, owner.image, owner.name)
    }
}
