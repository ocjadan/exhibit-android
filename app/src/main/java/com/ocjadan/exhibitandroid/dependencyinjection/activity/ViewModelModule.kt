package com.ocjadan.exhibitandroid.dependencyinjection.activity

import com.ocjadan.exhibitandroid.questions.questionDetails.FetchQuestionAnswersUseCase
import com.ocjadan.exhibitandroid.questions.questionsList.FetchQuestionsListItemsUseCase
import com.ocjadan.exhibitandroid.questions.questionDetails.QuestionDetailsViewModel
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListViewModel
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {
    @Provides
    fun questionsListVM(fetchQuestionsListItemsUseCase: FetchQuestionsListItemsUseCase) =
        QuestionsListViewModel(fetchQuestionsListItemsUseCase)

    @Provides
    fun questionDetailsVM(fetchQuestionAnswersUseCase: FetchQuestionAnswersUseCase) =
        QuestionDetailsViewModel(fetchQuestionAnswersUseCase)
}