package com.ocjadan.exhibitandroid.dependencyinjection.app

import android.app.Application
import android.content.Context
import com.ocjadan.exhibitandroid.common.TimeProvider
import com.ocjadan.exhibitandroid.database.owners.OwnersCache
import com.ocjadan.exhibitandroid.networking.UrlProvider
import com.ocjadan.exhibitandroid.questions.questionDetails.FetchQuestionAnswersUseCase
import com.ocjadan.exhibitandroid.questions.questionsList.FetchQuestionsUseCase
import com.ocjadan.exhibitandroid.networking.questionsList.FetchQuestionsEndpoint
import com.ocjadan.exhibitandroid.networking.questionDetails.FetchQuestionAnswersEndpoint
import com.ocjadan.exhibitandroid.database.questions.QuestionsCache
import com.ocjadan.exhibitandroid.database.updates.UpdatesCache
import dagger.Module
import dagger.Provides

@Module
internal object AppModule {
    @Provides
    fun getContext(application: Application): Context = application

    @Provides
    fun urlProvider() = UrlProvider()

    @Provides
    fun timeProvider() = TimeProvider()

    @Provides
    fun fetchQuestionsUseCase(
        endpoint: FetchQuestionsEndpoint,
        questionsCache: QuestionsCache,
        ownersCache: OwnersCache,
        updatesCache: UpdatesCache,
        timeProvider: TimeProvider
    ) = FetchQuestionsUseCase(endpoint, questionsCache, ownersCache, updatesCache, timeProvider)

    @Provides
    fun fetchQuestionAnswersUseCase(endpoint: FetchQuestionAnswersEndpoint) = FetchQuestionAnswersUseCase(endpoint)
}