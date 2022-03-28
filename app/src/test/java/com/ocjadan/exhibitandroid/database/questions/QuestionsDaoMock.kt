package com.ocjadan.exhibitandroid.database.questions

import com.ocjadan.exhibitandroid.database.EntityTestData
import com.ocjadan.exhibitandroid.database.questions.questionWithOwner.QuestionWithOwnerEntity
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

class QuestionsDaoMock {
    @Mock
    val mock: QuestionsDao = mock()

    fun success() {
        `when`(mock.getAllWithOwners(anyInt())).thenAnswer {
            val amount = it.arguments.first() as Int
            val result = mutableListOf<QuestionWithOwnerEntity>()

            for (i in 1..amount) {
                result.add(EntityTestData.getQuestionWithOwner())
            }

            return@thenAnswer result
        }
    }
}
