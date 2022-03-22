package com.ocjadan.exhibitandroid.questions.questionsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class QuestionsListViewModel(private val fetchQuestionsListItemsUseCase: FetchQuestionsListItemsUseCase) : ViewModel(),
    FetchQuestionsListItemsUseCase.Listener {

    enum class QuestionsListError {
        NETWORK, FAILURE
    }

    private val _questionsListItems: MutableLiveData<List<QuestionsListItem>> by lazy { MutableLiveData(listOf()) }
    val questionsListItems: LiveData<List<QuestionsListItem>> get() = _questionsListItems

    private val _error: MutableLiveData<QuestionsListError> by lazy { MutableLiveData(null) }
    val error: LiveData<QuestionsListError> get() = _error

    init {
        onCreate()
    }

    // Android provides 'onCleared' but not 'onCreate'; having one here for consistency
    private fun onCreate() {
        fetchQuestionsListItemsUseCase.addListener(this)
    }

    override fun onCleared() {
        fetchQuestionsListItemsUseCase.removeListener(this)
        super.onCleared()
    }

    fun loadQuestions() {
        viewModelScope.launch {
            fetchQuestionsListItemsUseCase.fetchQuestionsListItemsAndNotify()
        }
    }

    override fun onFetchQuestionsUseCaseSuccess(questionsListItems: List<QuestionsListItem>) {
        _questionsListItems.value = questionsListItems
        _error.value = null
    }

    override fun onFetchQuestionsUseCaseFailure() {
        _error.value = QuestionsListError.FAILURE
    }

    override fun onFetchQuestionsUseCaseNetworkError() {
        _error.value = QuestionsListError.NETWORK
    }
}