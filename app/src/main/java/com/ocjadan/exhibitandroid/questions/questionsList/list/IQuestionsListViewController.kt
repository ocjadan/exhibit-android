package com.ocjadan.exhibitandroid.questions.questionsList.list

import com.ocjadan.exhibitandroid.common.viewcontroller.IObservableViewController
import com.ocjadan.exhibitandroid.questions.questionsList.items.QuestionsListItem

interface IQuestionsListViewController : IObservableViewController<IQuestionsListViewController.Listener> {
    interface Listener {
        fun onQuestionsListItemClicked(questionsListItem: QuestionsListItem)
        fun onToolbarAvatarClicked()
    }

    fun bindQuestions(questionsListItems: List<QuestionsListItem>)
}