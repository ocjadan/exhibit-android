package com.ocjadan.exhibitandroid.networking

import com.ocjadan.exhibitandroid.questions.networking.QuestionSchemaTestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
class StackOverflowApiMock {
    @Mock
    val mock: StackOverflowApi = mock(StackOverflowApi::class.java)

    fun success() = runTest {
        `when`(mock.getQuestions())
            .thenReturn(
                Response.success(QuestionSchemaTestData.questionListSchema())
            )
    }

    fun generalError() = runTest {
        `when`(mock.getQuestions())
            .thenThrow(
                RuntimeException("general")
            )
    }

    fun networkError() = runTest {
        `when`(mock.getQuestions())
            .thenThrow(
                RuntimeException("network")
            )
    }
}