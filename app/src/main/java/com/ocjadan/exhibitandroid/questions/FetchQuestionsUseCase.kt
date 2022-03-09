package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.BaseObservable
import com.ocjadan.exhibitandroid.questions.networking.FetchQuestionsEndpoint
import com.ocjadan.exhibitandroid.questions.networking.FetchQuestionsEndpoint.FetchQuestionsEndpointStatus
import com.ocjadan.exhibitandroid.questions.networking.QuestionSchema

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
            FetchQuestionsEndpointStatus.SUCCESS -> {
                val questions = mapQuestionSchemaToQuestion(result.questions)
                notifySuccess(questions)
            }
            FetchQuestionsEndpointStatus.NETWORK_ERROR -> notifyNetworkError()
            else -> notifyFailure()
        }
    }

    private fun mapQuestionSchemaToQuestion(questions: List<QuestionSchema>): List<Question> {
        return questions.map { Question(it.title) }
    }

    private fun notifySuccess(questions: List<Question>) {
        for (listener in listenersMap) {
            listener.onFetchQuestionsUseCaseSuccess(questions)
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
