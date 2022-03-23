package com.ocjadan.exhibitandroid.questions.questionsList

import com.ocjadan.exhibitandroid.common.observable.BaseObservable
import com.ocjadan.exhibitandroid.users.Owner
import com.ocjadan.exhibitandroid.users.OwnerSchema
import com.ocjadan.exhibitandroid.questions.questionsList.networking.FetchQuestionsListItemsEndpoint
import com.ocjadan.exhibitandroid.questions.questionsList.networking.FetchQuestionsListItemsEndpoint.FetchQuestionsListItemsEndpointStatus
import com.ocjadan.exhibitandroid.questions.questionsList.networking.QuestionsListItemSchema

open class FetchQuestionsListItemsUseCase(private val fetchQuestionsListItemsEndpoint: FetchQuestionsListItemsEndpoint) :
    BaseObservable<FetchQuestionsListItemsUseCase.Listener>() {

    interface Listener {
        fun onFetchQuestionsUseCaseSuccess(questionsListItems: List<QuestionsListItem>)
        fun onFetchQuestionsUseCaseNetworkError()
        fun onFetchQuestionsUseCaseFailure()
    }

    open suspend fun fetchQuestionsListItemsAndNotify() {
        val result = fetchQuestionsListItemsEndpoint.fetchQuestionsListItems()
        when (result.status) {
            FetchQuestionsListItemsEndpointStatus.SUCCESS -> {
                val questionListItems = mapQuestionsListItemSchemaToQuestionListItem(result.questionsListItems)
                notifySuccess(questionListItems)
            }
            FetchQuestionsListItemsEndpointStatus.NETWORK_ERROR -> notifyNetworkError()
            else -> notifyFailure()
        }
    }

    private fun mapQuestionsListItemSchemaToQuestionListItem(questionsListItems: List<QuestionsListItemSchema>): List<QuestionsListItem> {
        val questionsWithId = questionsListItems.filter { it.question_id != null && it.owner != null }
        return questionsWithId.map {
            QuestionsListItem(
                it.question_id!!,
                it.title,
                mapQuestionsListItemOwnerSchemaToQuestionOwner(it.owner!!),
                it.is_answered
            )
        }
    }

    private fun mapQuestionsListItemOwnerSchemaToQuestionOwner(schema: OwnerSchema): Owner {
        return Owner(schema.account_id, schema.user_id, schema.profile_image, schema.display_name)
    }

    private fun notifySuccess(questions: List<QuestionsListItem>) {
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
