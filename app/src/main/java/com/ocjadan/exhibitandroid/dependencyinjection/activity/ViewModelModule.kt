package com.ocjadan.exhibitandroid.dependencyinjection.activity

import com.ocjadan.exhibitandroid.questionDetails.FetchQuestionAnswersUseCase
import com.ocjadan.exhibitandroid.questions.FetchQuestionsUseCase
import com.ocjadan.exhibitandroid.questionDetails.QuestionDetailsViewModel
import com.ocjadan.exhibitandroid.questions.QuestionsViewModel
import dagger.Module
import dagger.Provides

@Module
internal object ViewModelModule {
    @Provides
    fun questionsListVM(fetchQuestionsUseCase: FetchQuestionsUseCase) =
        QuestionsViewModel(fetchQuestionsUseCase)

    @Provides
    fun questionDetailsVM(fetchQuestionAnswersUseCase: FetchQuestionAnswersUseCase) =
        QuestionDetailsViewModel(fetchQuestionAnswersUseCase)
}