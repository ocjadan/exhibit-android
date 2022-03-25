package com.ocjadan.exhibitandroid.questions.questionDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ocjadan.benchmarkable.answers.Answer
import kotlinx.coroutines.launch

class QuestionDetailsViewModel(private val fetchQuestionAnswersUseCase: FetchQuestionAnswersUseCase) : ViewModel(),
    FetchQuestionAnswersUseCase.Listener {

    enum class QuestionDetailsError {
        NONE, FAILURE, NETWORK
    }

    private val _answers: MutableLiveData<List<Answer>> by lazy { MutableLiveData(emptyList()) }
    val answers: LiveData<List<Answer>> get() = _answers

    private val _error: MutableLiveData<QuestionDetailsError> by lazy { MutableLiveData(QuestionDetailsError.NONE) }
    val error: LiveData<QuestionDetailsError> get() = _error

    init {
        onCreate()
    }

    private fun onCreate() {
        fetchQuestionAnswersUseCase.addListener(this)
    }

    override fun onCleared() {
        fetchQuestionAnswersUseCase.removeListener(this)
        super.onCleared()
    }

    fun loadAnswers(id: Long) {
        viewModelScope.launch {
            fetchQuestionAnswersUseCase.fetchQuestionAnswers(id)
        }
    }

    override fun onFetchQuestionAnswersSuccess(answers: List<Answer>) {
        _answers.value = answers
    }

    override fun onFetchQuestionAnswersFailure() {
        _error.value = QuestionDetailsError.FAILURE
    }

    override fun onFetchQuestionAnswersNetworkError() {
        _error.value = QuestionDetailsError.NETWORK
    }
}
