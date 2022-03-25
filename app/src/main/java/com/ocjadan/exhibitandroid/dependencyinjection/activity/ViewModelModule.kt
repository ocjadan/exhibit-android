package com.ocjadan.exhibitandroid.dependencyinjection.activity

import com.ocjadan.exhibitandroid.questions.questionDetails.FetchQuestionAnswersUseCase
import com.ocjadan.exhibitandroid.questions.questionsList.FetchQuestionsUseCase
import com.ocjadan.exhibitandroid.questions.questionDetails.QuestionDetailsViewModel
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListViewModel
import dagger.Module
import dagger.Provides

@Module
internal object ViewModelModule {
    @Provides
    fun questionsListVM(fetchQuestionsUseCase: FetchQuestionsUseCase) =
        QuestionsListViewModel(fetchQuestionsUseCase)

    @Provides
    fun questionDetailsVM(fetchQuestionAnswersUseCase: FetchQuestionAnswersUseCase) =
        QuestionDetailsViewModel(fetchQuestionAnswersUseCase)
}