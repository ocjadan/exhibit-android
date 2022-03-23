package com.ocjadan.exhibitandroid.networking

import com.ocjadan.exhibitandroid.questions.SchemaTestData
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class StackOverflowApiMock {
    @Mock
    val mock: StackOverflowApi = mock(StackOverflowApi::class.java)

    fun success() = runTest {
        `when`(mock.getQuestionsListItems())
            .thenReturn(
                Response.success(SchemaTestData.questionListSchema())
            )
    }

    fun jsonError() = runTest {
        `when`(mock.getQuestionsListItems())
            .thenThrow(
                JsonDataException()
            )
    }

    fun networkError() = runTest {
        `when`(mock.getQuestionsListItems())
            .thenThrow(
                UnknownHostException()
            )
    }
}