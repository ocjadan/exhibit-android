package com.ocjadan.exhibitandroid.questionDetails

import com.ocjadan.exhibitandroid.common.observable.BaseObservable

class FetchQuestionAnswersUseCaseMock : FetchQuestionAnswersUseCase, BaseObservable<FetchQuestionAnswersUseCase.Listener>() {
    override suspend fun fetchQuestionAnswers(id: Long) {

    }
}
