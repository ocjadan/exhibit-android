package com.ocjadan.exhibitandroid.questions.questionsList.items

import com.ocjadan.exhibitandroid.common.TimeProviderMock
import com.ocjadan.exhibitandroid.database.questions.QuestionsCacheMock
import com.ocjadan.exhibitandroid.database.owners.OwnersCacheMock
import com.ocjadan.exhibitandroid.database.updates.UpdatesCacheMock
import com.ocjadan.exhibitandroid.questions.questionsList.FetchQuestionsUseCase

class FetchQuestionsUseCaseMock(
    private val fetchQuestionsEndpointMock: FetchQuestionsEndpointMock,
    private val questionsCacheMock: QuestionsCacheMock,
    private val ownersCacheMock: OwnersCacheMock,
    private val updatesCacheMock: UpdatesCacheMock,
    private val timeProviderMock: TimeProviderMock
) :
    FetchQuestionsUseCase(fetchQuestionsEndpointMock, questionsCacheMock, ownersCacheMock, updatesCacheMock, timeProviderMock) {
    var isGeneralError = false
    var isNetworkError = false

    override suspend fun fetchQuestionsAndNotify() {
        fetchQuestionsEndpointMock.isGeneralError = isGeneralError
        fetchQuestionsEndpointMock.isNetworkError = isNetworkError
        super.fetchQuestionsAndNotify()
    }
}