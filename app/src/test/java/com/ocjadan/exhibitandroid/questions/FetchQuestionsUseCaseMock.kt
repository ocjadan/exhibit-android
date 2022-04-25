package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.common.TestData
import com.ocjadan.exhibitandroid.common.TimeProviderMock
import com.ocjadan.exhibitandroid.database.questions.QuestionsCacheMock
import com.ocjadan.exhibitandroid.database.owners.OwnersCacheMock
import com.ocjadan.exhibitandroid.database.updates.UpdatesCacheMock
import com.ocjadan.exhibitandroid.networking.questions.FetchQuestionsEndpointMock
import kotlinx.coroutines.CoroutineDispatcher

class FetchQuestionsUseCaseMock(
    private val fetchQuestionsEndpointMock: FetchQuestionsEndpointMock,
    private val questionsCacheMock: QuestionsCacheMock,
    private val ownersCacheMock: OwnersCacheMock,
    private val updatesCacheMock: UpdatesCacheMock,
    private val timeProviderMock: TimeProviderMock,
    private val dispatcherBg: CoroutineDispatcher
) :
    FetchQuestionsUseCase(
        fetchQuestionsEndpointMock,
        questionsCacheMock,
        ownersCacheMock,
        updatesCacheMock,
        timeProviderMock,
        dispatcherBg
    ) {
    var isGeneralError = false
    var isNetworkError = false

    override suspend fun fetchQuestionsAndNotify() {
        when {
            isGeneralError -> notifyFailure()
            isNetworkError -> notifyNetworkError()
            else -> notifySuccess()
        }
    }

    private fun notifySuccess() {
        for (listener in getListeners()) {
            listener.onFetchQuestionsUseCaseSuccess(TestData.getQuestions())
        }
    }

    private fun notifyFailure() {
        for (listener in getListeners()) {
            listener.onFetchQuestionsUseCaseFailure()
        }
    }

    private fun notifyNetworkError() {
        for (listener in getListeners()) {
            listener.onFetchQuestionsUseCaseNetworkError()
        }
    }
}