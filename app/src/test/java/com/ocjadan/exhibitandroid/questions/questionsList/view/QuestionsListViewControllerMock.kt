package com.ocjadan.exhibitandroid.questions.questionsList.view

import android.view.View
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListItem

class QuestionsListViewControllerMock : IQuestionsListViewController {
    private val map = HashSet<IQuestionsListViewController.Listener>()

    override fun bindQuestions(questionsListItems: List<QuestionsListItem>) {
        TODO("Not yet implemented")
    }

    override fun getListeners(): Set<IQuestionsListViewController.Listener> {
        return map.toSet()
    }

    override fun addListener(listener: IQuestionsListViewController.Listener) {
        this.map.add(listener)
    }

    override fun removeListener(listener: IQuestionsListViewController.Listener) {
        this.map.remove(listener)
    }

    override fun getRootView(): View {
        TODO("Not yet implemented")
    }
}