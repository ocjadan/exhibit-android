package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.common.TestData
import com.ocjadan.exhibitandroid.common.observable.BaseObservable

class FetchQuestionsUseCaseMock : FetchQuestionsUseCase, BaseObservable<FetchQuestionsUseCase.Listener>() {
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
        notifyOnAllListeners { it.onFetchQuestionsUseCaseSuccess(TestData.getQuestions()) }
    }

    private fun notifyFailure() {
        notifyOnAllListeners { it.onFetchQuestionsUseCaseFailure() }
    }

    private fun notifyNetworkError() {
        notifyOnAllListeners { it.onFetchQuestionsUseCaseNetworkError() }
    }

    private fun notifyOnAllListeners(action: (listener: FetchQuestionsUseCase.Listener) -> Unit) {
        for (listener in getListeners()) {
            action(listener)
        }
    }
}