package com.ocjadan.exhibitandroid.questions.questionsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocjadan.exhibitandroid.questions.FetchQuestionsUseCase
import com.ocjadan.exhibitandroid.questions.Question

class QuestionsListViewModel(private val fetchQuestionsUseCase: FetchQuestionsUseCase) : ViewModel(),
    FetchQuestionsUseCase.Listener {

    enum class QuestionsListError {
        NETWORK, FAILURE
    }

    private val _questions: MutableLiveData<List<Question>> by lazy { MutableLiveData(listOf()) }
    val questions: LiveData<List<Question>> get() = _questions

    private val _error: MutableLiveData<QuestionsListError> by lazy { MutableLiveData(null) }
    val error: LiveData<QuestionsListError> get() = _error

    init {
        fetchQuestionsUseCase.addListener(this)
    }

    override fun onCleared() {
        fetchQuestionsUseCase.removeListener(this)
        super.onCleared()
    }

    suspend fun loadQuestions() {
        fetchQuestionsUseCase.fetchQuestionsAndNotify()
    }

    override fun onFetchQuestionsUseCaseSuccess(questions: List<Question>) {
        _questions.value = questions
        _error.value = null
    }

    override fun onFetchQuestionsUseCaseFailure() {
        _error.value = QuestionsListError.FAILURE
    }

    override fun onFetchQuestionsUseCaseNetworkError() {
        _error.value = QuestionsListError.NETWORK
    }
}