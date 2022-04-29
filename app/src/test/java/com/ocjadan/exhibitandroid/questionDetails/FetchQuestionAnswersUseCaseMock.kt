package com.ocjadan.exhibitandroid.questionDetails

import com.ocjadan.exhibitandroid.networking.questions.FetchQuestionAnswersEndpointMock
import kotlinx.coroutines.CoroutineDispatcher

class FetchQuestionAnswersUseCaseMock(
    fetchQuestionAnswersEndpointMock: FetchQuestionAnswersEndpointMock,
    dispatcher: CoroutineDispatcher
) :
    FetchQuestionAnswersUseCaseImpl(fetchQuestionAnswersEndpointMock, dispatcher) {

}
