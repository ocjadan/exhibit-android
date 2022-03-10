package com.ocjadan.exhibitandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ocjadan.exhibitandroid.questions.QuestionsListViewModel
import java.lang.RuntimeException
import javax.inject.Provider

class ViewModelFactory(private val questionsListVM: Provider<QuestionsListViewModel>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            QuestionsListViewModel::class.java -> questionsListVM.get() as T
            else -> throw RuntimeException("Unexpected ViewModel: $modelClass")
        }
    }
}
