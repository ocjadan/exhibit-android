package com.ocjadan.exhibitandroid.questions.questionsList

import android.view.View
import com.ocjadan.exhibitandroid.questions.Question

class QuestionsListViewControllerMock : IQuestionsListViewController {
    private val map = HashSet<IQuestionsListViewController.Listener>()

    override fun bindQuestions(questions: List<Question>) {
        TODO("Not yet implemented")
    }

    override val listenersMap: Set<IQuestionsListViewController.Listener>
        get() = map

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