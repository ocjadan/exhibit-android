package com.ocjadan.exhibitandroid.questions.questionsList

import com.ocjadan.exhibitandroid.common.TimeProvider
import com.ocjadan.exhibitandroid.common.observable.BaseObservable
import com.ocjadan.exhibitandroid.database.owners.OwnersCache
import com.ocjadan.exhibitandroid.database.questions.QuestionsCache
import com.ocjadan.exhibitandroid.database.updates.UpdatesCache
import com.ocjadan.exhibitandroid.owners.Owner
import com.ocjadan.exhibitandroid.networking.questionsList.FetchQuestionsEndpoint
import com.ocjadan.exhibitandroid.networking.questionsList.FetchQuestionsEndpoint.FetchQuestionsEndpointStatus

open class FetchQuestionsUseCase(
    private val fetchQuestionsEndpoint: FetchQuestionsEndpoint,
    private val questionsCache: QuestionsCache,
    private val ownersCache: OwnersCache,
    private val updatesCache: UpdatesCache,
    private val timeProvider: TimeProvider
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

    private fun shouldFetchQuestionsFromEndpoint(): Boolean {
        val lastQuestionsUpdate = updatesCache.getLastQuestionsUpdate() ?: return true
        return lastQuestionsUpdate + CACHE_TIMEOUT < timeProvider.getCurrentTimestamp()
    }

    private suspend fun fetchQuestionsFromEndpointAndNotify() {
        val result = fetchQuestionsEndpoint.fetchQuestions()
        when (result.status) {
            FetchQuestionsEndpointStatus.SUCCESS -> {
                saveQuestionsToCache(result.questions)
                fetchQuestionsFromCacheAndNotify()
            }
            FetchQuestionsEndpointStatus.NETWORK_ERROR -> notifyNetworkError()
            else -> notifyFailure()
        }
    }

    private fun fetchQuestionsFromCacheAndNotify() {
        val questions = questionsCache.getQuestionsWithOwners()
        notifySuccess(questions)
    }

    private fun saveQuestionsToCache(questions: List<Question>) {
        val owners = mapOwnersFromQuestions(questions)
        ownersCache.saveAll(owners)
        questionsCache.saveAll(questions)
        updatesCache.setLastQuestionsUpdate(timeProvider.getCurrentTimestamp())
    }

    private fun mapOwnersFromQuestions(questions: List<Question>): List<Owner> {
        return questions.map { Owner(it.owner.accountId, it.owner.userId, it.owner.profileImage, it.owner.name) }
    }

    private fun notifySuccess(questions: List<Question>) {
        for (listener in getListeners()) {
            listener.onFetchQuestionsUseCaseSuccess(questions)
        }
    }

    private fun notifyFailure() {
        for (listener in getListeners()) {
            listener.onFetchQuestionsUseCaseFailure()
        }
    }

    private fun notifyNetworkError() {
        for (listener in getListeners()) {
            listener.onFetchQuestionsUseCaseNetworkError()
        }
    }
}
