package com.ocjadan.exhibitandroid.dependencyinjection.activity

import com.ocjadan.exhibitandroid.questions.FetchQuestionsUseCase
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListViewModel
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {
    @Provides
    fun questionsListVM(fetchQuestionsUseCase: FetchQuestionsUseCase) = QuestionsListViewModel(fetchQuestionsUseCase)
}