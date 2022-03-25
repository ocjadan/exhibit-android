package com.ocjadan.exhibitandroid.questions.questionsList.views

import android.view.View
import com.ocjadan.exhibitandroid.questions.questionsList.Question

class QuestionsListViewControllerMock : IQuestionsListViewController {
    private val map = HashSet<IQuestionsListViewController.Listener>()

    override fun bindQuestions(questions: List<Question>) {
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