package com.ocjadan.exhibitandroid.dependencyinjection.app

import com.ocjadan.exhibitandroid.UrlProvider
import com.ocjadan.exhibitandroid.questions.FetchQuestionsUseCase
import com.ocjadan.exhibitandroid.questions.networking.FetchQuestionsEndpoint
import dagger.Module
import dagger.Provides

@Module
internal object AppModule {
    @Provides
    fun urlProvider() = UrlProvider()

    @Provides
    fun fetchQuestionsUseCase(endpoint: FetchQuestionsEndpoint) = FetchQuestionsUseCase(endpoint)
}