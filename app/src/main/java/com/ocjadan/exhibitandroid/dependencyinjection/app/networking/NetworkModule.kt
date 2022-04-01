package com.ocjadan.exhibitandroid.dependencyinjection.app.networking

import com.ocjadan.exhibitandroid.networking.UrlProvider
import com.ocjadan.exhibitandroid.dependencyinjection.app.AppScope
import com.ocjadan.exhibitandroid.dependencyinjection.app.coroutines.DispatcherIO
import com.ocjadan.exhibitandroid.networking.StackOverflowApi
import com.ocjadan.exhibitandroid.networking.questionsList.FetchQuestionsEndpoint
import com.ocjadan.exhibitandroid.networking.questionDetails.FetchQuestionAnswersEndpoint

import dagger.Module
import dagger.Provides

import kotlinx.coroutines.CoroutineDispatcher

import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import java.util.concurrent.TimeUnit

@Module
internal object NetworkModule {
    @AppScope
    @Provides
    @RetrofitStackExchange
    fun retrofit(urlProvider: UrlProvider, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlProvider.stackExchange())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun stackOverflowApi(@RetrofitStackExchange retrofit: Retrofit): StackOverflowApi {
        return retrofit.create(StackOverflowApi::class.java)
    }

    @Provides
    fun fetchQuestionsEndpoint(api: StackOverflowApi, @DispatcherIO dispatcher: CoroutineDispatcher) =
        FetchQuestionsEndpoint(api, dispatcher)

    @Provides
    fun fetchQuestionAnswersEndpoint(api: StackOverflowApi, @DispatcherIO dispatcher: CoroutineDispatcher) =
        FetchQuestionAnswersEndpoint(api, dispatcher)
}