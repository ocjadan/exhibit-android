package com.ocjadan.exhibitandroid.questions.questionDetails

import com.ocjadan.benchmarkable.questionDetails.QuestionAnswer
import com.ocjadan.exhibitandroid.common.observable.BaseObservable
import com.ocjadan.exhibitandroid.networking.questionDetails.FetchQuestionAnswersEndpoint
import com.ocjadan.exhibitandroid.networking.questionDetails.FetchQuestionAnswersEndpoint.FetchQuestionAnswersEndpointStatus

import java.lang.RuntimeException

open class FetchQuestionAnswersUseCase(private val fetchQuestionAnswersEndpoint: FetchQuestionAnswersEndpoint) :
    BaseObservable<FetchQuestionAnswersUseCase.Listener>() {
    interface Listener {
        fun onFetchQuestionAnswersSuccess(questionAnswers: List<QuestionAnswer>)
        fun onFetchQuestionAnswersFailure()
        fun onFetchQuestionAnswersNetworkError()
    }

    suspend fun fetchQuestionAnswers(id: Long) {
        val result = fetchQuestionAnswersEndpoint.fetchQuestionAnswers(id)
        when (result.status) {
            FetchQuestionAnswersEndpointStatus.SUCCESS -> {
                val answers = result.answers ?: throw RuntimeException("Answers null on success ${result.answers}")
                notifySuccess(answers)
            }
            FetchQuestionAnswersEndpointStatus.FAILURE -> notifyFailure()
            FetchQuestionAnswersEndpointStatus.NETWORK_ERROR -> notifyNetworkError()
        }
    }

    private fun notifySuccess(questionAnswers: List<QuestionAnswer>) {
        for (listener in getListeners()) {
            listener.onFetchQuestionAnswersSuccess(questionAnswers)
        }
    }

    private fun notifyFailure() {
        for (listener in getListeners()) {
            listener.onFetchQuestionAnswersFailure()
        }
    }

    private fun notifyNetworkError() {
        for (listener in getListeners()) {
            listener.onFetchQuestionAnswersNetworkError()
        }
    }
}
