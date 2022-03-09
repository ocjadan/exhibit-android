package com.ocjadan.exhibitandroid.questions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionsListViewModel(private val fetchQuestionsUseCase: FetchQuestionsUseCase) : ViewModel(),
    FetchQuestionsUseCase.Listener {

    enum class QuestionsListError {
        NETWORK, FAILURE
    }

    private val _questions: MutableList<Question> by lazy { mutableListOf() }
    val questions: List<Question> = _questions

    private val _error: MutableLiveData<QuestionsListError> by lazy { MutableLiveData(null) }
    val error: QuestionsListError? get() = _error.value

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
        _questions.addAll(questions)
        _error.value = null
    }

    override fun onFetchQuestionsUseCaseFailure() {
        _error.value = QuestionsListError.FAILURE
    }

    override fun onFetchQuestionsUseCaseNetworkError() {
        _error.value = QuestionsListError.NETWORK
    }
}