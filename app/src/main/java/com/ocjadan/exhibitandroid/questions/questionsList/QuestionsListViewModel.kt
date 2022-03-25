package com.ocjadan.exhibitandroid.questions.questionsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class QuestionsListViewModel(private val fetchQuestionsUseCase: FetchQuestionsUseCase) : ViewModel(),
    FetchQuestionsUseCase.Listener {

    enum class QuestionsListError {
        NETWORK, FAILURE
    }

    private val _questions: MutableLiveData<List<Question>> by lazy { MutableLiveData(listOf()) }
    val questions: LiveData<List<Question>> get() = _questions

    private val _error: MutableLiveData<QuestionsListError> by lazy { MutableLiveData(null) }
    val error: LiveData<QuestionsListError> get() = _error

    init {
        onCreate()
    }

    // Android provides 'onCleared' but not 'onCreate'; having one here for consistency
    private fun onCreate() {
        fetchQuestionsUseCase.addListener(this)
    }

    override fun onCleared() {
        fetchQuestionsUseCase.removeListener(this)
        super.onCleared()
    }

    fun loadQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchQuestionsUseCase.fetchQuestionsAndNotify()
        }
    }

    override fun onFetchQuestionsUseCaseSuccess(questions: List<Question>) {
        viewModelScope.launch {
            _questions.value = questions
            _error.value = null
        }
    }

    override fun onFetchQuestionsUseCaseFailure() {
        viewModelScope.launch {
            _error.value = QuestionsListError.FAILURE
        }
    }

    override fun onFetchQuestionsUseCaseNetworkError() {
        viewModelScope.launch {
            _error.value = QuestionsListError.NETWORK
        }
    }
}