package com.ocjadan.exhibitandroid.questions

import com.ocjadan.exhibitandroid.common.TimeProvider
import com.ocjadan.exhibitandroid.common.observable.BaseObservable
import com.ocjadan.exhibitandroid.database.owners.OwnersCache
import com.ocjadan.exhibitandroid.database.questions.QuestionsCache
import com.ocjadan.exhibitandroid.database.updates.UpdatesCache
import com.ocjadan.exhibitandroid.owners.Owner
import com.ocjadan.exhibitandroid.networking.questionsList.FetchQuestionsEndpoint
import com.ocjadan.exhibitandroid.networking.questionsList.FetchQuestionsEndpoint.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class FetchQuestionsUseCase(
    private val fetchQuestionsEndpoint: FetchQuestionsEndpoint,
    private val questionsCache: QuestionsCache,
    private val ownersCache: OwnersCache,
    private val updatesCache: UpdatesCache,
    private val timeProvider: TimeProvider,
    private val dispatcher: CoroutineDispatcher
) : BaseObservable<FetchQuestionsUseCase.Listener>() {
    companion object {
        private const val CACHE_TIMEOUT = 60_000L // 1 minute
    }

    interface Listener {
        fun onFetchQuestionsUseCaseSuccess(questions: List<Question>)
        fun onFetchQuestionsUseCaseNetworkError()
        fun onFetchQuestionsUseCaseFailure()
    }

    open suspend fun fetchQuestionsAndNotify() {
        if (shouldFetchQuestionsFromEndpoint()) {
            fetchQuestionsFromEndpointAndNotify()
        } else {
            fetchQuestionsFromCacheAndNotify()
        }
    }

    private suspend fun shouldFetchQuestionsFromEndpoint(): Boolean {
        val lastQuestionsUpdate = updatesCache.getLastQuestionsUpdate() ?: return true
        return lastQuestionsUpdate + CACHE_TIMEOUT < timeProvider.getCurrentTimestamp()
    }

    private suspend fun fetchQuestionsFromEndpointAndNotify() {
        when (val result = fetchQuestionsEndpoint.fetchQuestions()) {
            is Result.Success -> onFetchQuestionsFromEndpointSuccess(result.questions)
            is Result.NetworkError -> notifyNetworkError()
            else -> notifyFailure()
        }
    }

    private suspend fun fetchQuestionsFromCacheAndNotify() {
        val questions = questionsCache.getQuestions()
        notifySuccess(questions)
    }

    private suspend fun onFetchQuestionsFromEndpointSuccess(questions: List<Question>) {
        saveToCache(questions)
        fetchQuestionsFromCacheAndNotify()
    }

    private suspend fun saveToCache(questions: List<Question>) = withContext(dispatcher) {
        launch {
            val owners = mapOwnersFromQuestions(questions)
            ownersCache.saveAll(owners)
        }

        launch {
            questionsCache.saveAll(questions)
        }

        launch {
            updatesCache.setLastQuestionsUpdate(timeProvider.getCurrentTimestamp())
        }
    }

    private suspend fun notifySuccess(questions: List<Question>) = withContext(dispatcher) {
        for (listener in getListeners()) {
            launch {
                listener.onFetchQuestionsUseCaseSuccess(questions)
            }
        }
    }

    private suspend fun notifyFailure() = withContext(dispatcher) {
        for (listener in getListeners()) {
            launch {
                listener.onFetchQuestionsUseCaseFailure()
            }
        }
    }

    private suspend fun notifyNetworkError() = withContext(dispatcher) {
        for (listener in getListeners()) {
            launch {
                listener.onFetchQuestionsUseCaseNetworkError()
            }
        }
    }

    private fun mapOwnersFromQuestions(questions: List<Question>): List<Owner> {
        return questions.map { Owner(it.owner.accountId, it.owner.userId, it.owner.profileImage, it.owner.name) }
    }
}
