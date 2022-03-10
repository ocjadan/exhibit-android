package com.ocjadan.exhibitandroid.dependencyinjection.activity

import com.ocjadan.exhibitandroid.ViewModelFactory
import com.ocjadan.exhibitandroid.questions.QuestionsListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
object ActivityModule {
    @Provides
    fun viewModelFactory(questionsListVM: Provider<QuestionsListViewModel>) = ViewModelFactory(questionsListVM)
}