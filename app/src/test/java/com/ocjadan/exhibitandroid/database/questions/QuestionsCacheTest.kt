package com.ocjadan.exhibitandroid.database.questions

import com.ocjadan.exhibitandroid.common.TestData
import com.ocjadan.exhibitandroid.database.EntityTestData
import com.ocjadan.exhibitandroid.dependencyinjection.CompositionRoot

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class QuestionsCacheTest {

    private lateinit var SUT: QuestionsCache
    private lateinit var questionsDao: QuestionsDaoMock

    @Before
    fun setUp() {
        val compositionRoot = CompositionRoot()
        val dispatcher = compositionRoot.testDispatcher

        questionsDao = compositionRoot.getQuestionsDaoMock()
        SUT = QuestionsCache(questionsDao.mock, dispatcher)

        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getQuestions_success_questionsReturned() = runTest {
        success()

        val amount = 10
        val questions = SUT.getQuestions(amount)

        assert(questions.isNotEmpty())
        assert(questions.count() == amount)
    }

    @Test
    fun saveAll_success_insertCalledWithQuestions() = runTest {
        success()

        val amount = 10
        val questions = TestData.getQuestions(amount)
        SUT.saveAll(questions)

        val questionEntities = EntityTestData.mapQuestionsToQuestionEntities(questions)
        verify(questionsDao.mock).upsertAll(questionEntities)
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Region: Helper Methods
    // ------------------------------------------------------------------------------------------------------------------

    private fun success() {
        questionsDao.success()
    }
}