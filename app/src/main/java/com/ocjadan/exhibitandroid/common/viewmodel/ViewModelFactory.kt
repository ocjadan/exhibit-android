package com.ocjadan.exhibitandroid.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ocjadan.exhibitandroid.questionDetails.QuestionDetailsViewModel
import com.ocjadan.exhibitandroid.questions.QuestionsViewModel
import java.lang.RuntimeException
import javax.inject.Provider

class ViewModelFactory(
    private val questionsVM: Provider<QuestionsViewModel>,
    private val questionDetailsVM: Provider<QuestionDetailsViewModel>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            QuestionsViewModel::class.java -> questionsVM.get() as T
            QuestionDetailsViewModel::class.java -> questionDetailsVM.get() as T
            else -> throw RuntimeException("Unexpected ViewModel: $modelClass")
        }
    }
}
