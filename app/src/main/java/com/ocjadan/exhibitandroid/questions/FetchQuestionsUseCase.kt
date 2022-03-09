package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.BaseObservable
import com.ocjadan.exhibitandroid.questions.FetchQuestionsEndpoint.FetchQuestionsEndpointStatus

open class FetchQuestionsUseCase(private val fetchQuestionsEndpoint: FetchQuestionsEndpoint) :
    BaseObservable<FetchQuestionsUseCase.Listener>() {

    interface Listener {
        fun onFetchQuestionsUseCaseSuccess(questions: List<Question>)
        fun onFetchQuestionsUseCaseNetworkError()
        fun onFetchQuestionsUseCaseFailure()
    }

    open suspend fun fetchQuestionsAndNotify() {
        val result = fetchQuestionsEndpoint.fetchQuestions()
        when (result.status) {
            FetchQuestionsEndpointStatus.SUCCESS -> notifySuccess()
            FetchQuestionsEndpointStatus.NETWORK_ERROR -> notifyNetworkError()
            else -> notifyFailure()
        }
    }

    private fun notifySuccess() {
        for (listener in listenersMap) {
            listener.onFetchQuestionsUseCaseSuccess(listOf(Question("")))
        }
    }

    private fun notifyFailure() {
        for (listener in listenersMap) {
            listener.onFetchQuestionsUseCaseFailure()
        }
    }

    private fun notifyNetworkError() {
        for (listener in listenersMap) {
            listener.onFetchQuestionsUseCaseNetworkError()
        }
    }
}
