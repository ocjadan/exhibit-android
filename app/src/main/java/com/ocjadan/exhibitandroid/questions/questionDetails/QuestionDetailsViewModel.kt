package com.ocjadan.exhibitandroid.questions.questionDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocjadan.benchmarkable.questionDetails.QuestionDetailsItem

class QuestionDetailsViewModel : ViewModel() {

    private val _questionDetailsItem: MutableLiveData<QuestionDetailsItem?> = MutableLiveData(null)
    val questionDetailsItem: LiveData<QuestionDetailsItem?> get() = _questionDetailsItem

    fun loadQuestion(id: Int) {

    }
}
