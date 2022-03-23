package com.ocjadan.exhibitandroid.questions.questionDetails

import android.text.Html
import com.ocjadan.benchmarkable.answers.Answer
import com.ocjadan.exhibitandroid.answers.AnswerSchema
import com.ocjadan.exhibitandroid.common.observable.BaseObservable
import com.ocjadan.exhibitandroid.questions.questionDetails.networking.FetchQuestionAnswersEndpoint
import com.ocjadan.exhibitandroid.questions.questionDetails.networking.FetchQuestionAnswersEndpoint.FetchQuestionAnswersEndpointStatus

import java.lang.RuntimeException

class FetchQuestionAnswersUseCase(private val fetchQuestionAnswersEndpoint: FetchQuestionAnswersEndpoint) :
    BaseObservable<FetchQuestionAnswersUseCase.Listener>() {
    interface Listener {
        fun onFetchQuestionAnswersSuccess(answers: List<Answer>)
        fun onFetchQuestionAnswersFailure()
        fun onFetchQuestionAnswersNetworkError()
    }

    suspend fun fetchQuestionAnswers(id: Int) {
        val result = fetchQuestionAnswersEndpoint.fetchQuestionAnswers(id)
        when (result.status) {
            FetchQuestionAnswersEndpointStatus.SUCCESS -> {
                val answerSchemas = result.answers ?: throw RuntimeException("Schema null on success ${result.answers}")
                val answers = mapAnswersListItemSchemaToAnswersList(answerSchemas)
                notifySuccess(answers)
            }
            FetchQuestionAnswersEndpointStatus.FAILURE -> notifyFailure()
            FetchQuestionAnswersEndpointStatus.NETWORK_ERROR -> notifyNetworkError()
        }
    }

    private fun mapAnswersListItemSchemaToAnswersList(schema: List<AnswerSchema>): List<Answer> {
        return schema.map { Answer(it.id, Html.fromHtml(it.body).toString()) }
    }

    private fun notifySuccess(answers: List<Answer>) {
        for (listener in getListeners()) {
            listener.onFetchQuestionAnswersSuccess(answers)
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
