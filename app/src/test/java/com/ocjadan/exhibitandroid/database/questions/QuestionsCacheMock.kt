package com.ocjadan.exhibitandroid.database.questions

import com.ocjadan.exhibitandroid.common.TestData
import com.ocjadan.exhibitandroid.database.EntityTestData
import com.ocjadan.exhibitandroid.questions.questionsList.Question

class QuestionsCacheMock(private val questionsDaoMock: QuestionsDaoMock) : QuestionsCache(questionsDaoMock.mock) {
    override suspend fun getQuestions(amount: Int): List<Question> {
        questionsDaoMock.success()

        val entities = questionsDaoMock.mock.getAllWithOwners(amount)
        return TestData.mapQuestionWithOwnerEntitiesToQuestions(entities)
    }

    override suspend fun saveAll(questions: List<Question>) {
        questionsDaoMock.success()

        val entities = EntityTestData.mapQuestionsToQuestionEntities(questions)
        questionsDaoMock.mock.insertAll(entities)
    }
}