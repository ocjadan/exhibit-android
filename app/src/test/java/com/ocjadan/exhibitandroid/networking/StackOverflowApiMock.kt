package com.ocjadan.exhibitandroid.networking

import com.ocjadan.exhibitandroid.questions.QuestionsTestData
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock

class StackOverflowApiMock {
    @Mock
    val mock: StackOverflowApi = mock(StackOverflowApi::class.java)

    fun success() {
        Mockito.`when`(mock.getQuestions()).thenReturn(QuestionsTestData.questionData())
    }

    fun generalError() {
        Mockito.`when`(mock.getQuestions()).thenThrow(RuntimeException("general"))
    }

    fun networkError() {
        Mockito.`when`(mock.getQuestions()).thenThrow(RuntimeException("network"))
    }
}