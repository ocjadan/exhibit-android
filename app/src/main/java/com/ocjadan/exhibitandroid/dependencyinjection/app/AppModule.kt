package com.ocjadan.exhibitandroid.dependencyinjection.app

import com.ocjadan.exhibitandroid.common.UrlProvider
import com.ocjadan.exhibitandroid.questions.questionDetails.FetchQuestionAnswersUseCase
import com.ocjadan.exhibitandroid.questions.questionsList.FetchQuestionsListItemsUseCase
import com.ocjadan.exhibitandroid.questions.questionsList.networking.FetchQuestionsListItemsEndpoint
import com.ocjadan.exhibitandroid.questions.questionDetails.networking.FetchQuestionAnswersEndpoint
import dagger.Module
import dagger.Provides

@Module
internal object AppModule {
    @Provides
    fun urlProvider() = UrlProvider()

    @Provides
    fun fetchQuestionsListItemsUseCase(endpoint: FetchQuestionsListItemsEndpoint) = FetchQuestionsListItemsUseCase(endpoint)

    @Provides
    fun fetchQuestionAnswersUseCase(endpoint: FetchQuestionAnswersEndpoint) = FetchQuestionAnswersUseCase(endpoint)
}