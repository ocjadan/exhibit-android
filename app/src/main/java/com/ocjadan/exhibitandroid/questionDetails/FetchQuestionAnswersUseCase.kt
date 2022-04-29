package com.ocjadan.exhibitandroid.questionDetails

import com.ocjadan.benchmarkable.questionDetails.QuestionAnswer
import com.ocjadan.exhibitandroid.common.observable.BaseObservable
import com.ocjadan.exhibitandroid.networking.questionDetails.FetchQuestionAnswersEndpoint
import com.ocjadan.exhibitandroid.networking.questionDetails.FetchQuestionAnswersEndpoint.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class FetchQuestionAnswersUseCase(
    private val fetchQuestionAnswersEndpoint: FetchQuestionAnswersEndpoint,
    private val dispatcher: CoroutineDispatcher
) :
    BaseObservable<FetchQuestionAnswersUseCase.Listener>() {
    interface Listener {
        fun onFetchQuestionAnswersSuccess(questionAnswers: List<QuestionAnswer>)
        fun onFetchQuestionAnswersFailure()
        fun onFetchQuestionAnswersNetworkError()
    }

    suspend fun fetchQuestionAnswers(id: Long) {
        when (val result = fetchQuestionAnswersEndpoint.fetchQuestionAnswers(id)) {
            is Result.Success -> notifySuccess(result.answers)
            is Result.Failure -> notifyFailure()
            is Result.NetworkError -> notifyNetworkError()
        }
    }

    private suspend fun notifySuccess(questionAnswers: List<QuestionAnswer>) = withContext(dispatcher) {
        launchOnAllListeners { it.onFetchQuestionAnswersSuccess(questionAnswers) }
    }

    private suspend fun notifyFailure() = withContext(dispatcher) {
        launchOnAllListeners { it.onFetchQuestionAnswersFailure() }
    }

    private suspend fun notifyNetworkError() = withContext(dispatcher) {
        launchOnAllListeners { it.onFetchQuestionAnswersNetworkError() }
    }

    private suspend fun launchOnAllListeners(action: (listener: Listener) -> Unit) = withContext(dispatcher) {
        for (listener in getListeners()) {
            launch {
                action(listener)
            }
        }
    }
}
