package com.ocjadan.exhibitandroid.dependencyinjection.app.networking

import com.ocjadan.exhibitandroid.UrlProvider
import com.ocjadan.exhibitandroid.dependencyinjection.app.AppScope
import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.ocjadan.exhibitandroid.questions.networking.FetchQuestionsEndpoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
internal object NetworkModule {
    @AppScope
    @Provides
    @RetrofitStackExchange
    fun retrofit(urlProvider: UrlProvider): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlProvider.stackExchange())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun stackOverflowApi(@RetrofitStackExchange retrofit: Retrofit): StackOverflowApi {
        return retrofit.create(StackOverflowApi::class.java)
    }

    @Provides
    fun fetchQuestionsEndpoint(api: StackOverflowApi) = FetchQuestionsEndpoint(api)
}