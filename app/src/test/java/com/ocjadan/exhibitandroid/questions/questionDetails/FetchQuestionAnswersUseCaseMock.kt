package com.ocjadan.exhibitandroid.questions.questionDetails

import com.ocjadan.exhibitandroid.networking.questions.FetchQuestionAnswersEndpointMock
import kotlinx.coroutines.CoroutineDispatcher

class FetchQuestionAnswersUseCaseMock(
    fetchQuestionAnswersEndpointMock: FetchQuestionAnswersEndpointMock,
    dispatcher: CoroutineDispatcher
) :
    FetchQuestionAnswersUseCase(fetchQuestionAnswersEndpointMock, dispatcher) {

}
