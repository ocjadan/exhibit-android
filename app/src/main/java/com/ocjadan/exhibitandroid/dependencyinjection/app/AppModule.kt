package com.ocjadan.exhibitandroid.dependencyinjection.app

import com.ocjadan.exhibitandroid.common.UrlProvider
import com.ocjadan.exhibitandroid.questions.questionsList.FetchQuestionsListItemsUseCase
import com.ocjadan.exhibitandroid.questions.networking.FetchQuestionsListItemsEndpoint
import dagger.Module
import dagger.Provides

@Module
internal object AppModule {
    @Provides
    fun urlProvider() = UrlProvider()

    @Provides
    fun fetchQuestionsListItemsUseCase(endpoint: FetchQuestionsListItemsEndpoint) = FetchQuestionsListItemsUseCase(endpoint)
}