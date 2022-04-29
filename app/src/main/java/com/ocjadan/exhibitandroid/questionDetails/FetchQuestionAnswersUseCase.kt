package com.ocjadan.exhibitandroid.questionDetails

import com.ocjadan.benchmarkable.questionDetails.QuestionAnswer
import com.ocjadan.exhibitandroid.common.observable.Observable

interface FetchQuestionAnswersUseCase : Observable<FetchQuestionAnswersUseCase.Listener> {
    interface Listener {
        fun onFetchQuestionAnswersSuccess(questionAnswers: List<QuestionAnswer>)
        fun onFetchQuestionAnswersFailure()
        fun onFetchQuestionAnswersNetworkError()
    }

    suspend fun fetchQuestionAnswers(id: Long)
}