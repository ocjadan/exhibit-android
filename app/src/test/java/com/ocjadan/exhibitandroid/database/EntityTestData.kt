package com.ocjadan.exhibitandroid.database

import com.ocjadan.exhibitandroid.RandomData
import com.ocjadan.exhibitandroid.database.owners.OwnerEntity
import com.ocjadan.exhibitandroid.database.questions.QuestionEntity
import com.ocjadan.exhibitandroid.database.questions.questionWithOwner.QuestionWithOwnerEntity
import com.ocjadan.exhibitandroid.database.updates.lastQuestionsUpdate.LastQuestionsUpdateEntity
import com.ocjadan.exhibitandroid.owners.Owner
import com.ocjadan.exhibitandroid.questions.questionsList.Question

object EntityTestData {
    fun getOwnerEntity(): OwnerEntity {
        val randomLongs = RandomData.getRandomLongs(2)
        val id = randomLongs.first()
        val accountId = randomLongs.last()
        return OwnerEntity(id, "NAME_$id", "IMAGE_$id", accountId)
    }

    fun getQuestionEntity(): QuestionEntity {
        val randomLongs = RandomData.getRandomLongs(3)
        val questionId = randomLongs[0]
        val ownerId = randomLongs[1]
        val creationDate = randomLongs[2]
        val tableOrderId = RandomData.getRandomInt()
        val isAnswered = questionId % 2 == 0L
        return QuestionEntity(questionId, isAnswered, "TITLE_$questionId", creationDate, ownerId, tableOrderId)
    }

    fun getQuestionWithOwner(): QuestionWithOwnerEntity {
        return QuestionWithOwnerEntity(getQuestionEntity(), getOwnerEntity())
    }

    fun getLastQuestionsUpdateEntity(timestamp: Long): LastQuestionsUpdateEntity {
        return LastQuestionsUpdateEntity(RandomData.getRandomInt(), timestamp)
    }

    fun mapOwnersToOwnerEntities(owners: List<Owner>): List<OwnerEntity> {
        return owners.map { mapOwnerToOwnerEntity(it) }
    }

    private fun mapOwnerToOwnerEntity(owner: Owner): OwnerEntity {
        return OwnerEntity(owner.userId, owner.name, owner.profileImage, owner.accountId)
    }

    fun mapQuestionsToQuestionEntities(questions: List<Question>): List<QuestionEntity> {
        return questions.map { QuestionEntity(it.id, it.isAnswered, it.title, it.creationDate, it.owner.userId) }
    }
}