package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.common.observable.Observable

interface FetchQuestionsUseCase : Observable<FetchQuestionsUseCase.Listener> {
    interface Listener {
        fun onFetchQuestionsUseCaseSuccess(questions: List<Question>)
        fun onFetchQuestionsUseCaseNetworkError()
        fun onFetchQuestionsUseCaseFailure()
    }

    suspend fun fetchQuestionsAndNotify()
}