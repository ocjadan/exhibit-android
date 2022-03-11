package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.common.BaseObservable
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
        val questionsWithId = questions.filter { it.question_id != null }
        return questionsWithId.map { Question(it.question_id!!, it.title) }
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
