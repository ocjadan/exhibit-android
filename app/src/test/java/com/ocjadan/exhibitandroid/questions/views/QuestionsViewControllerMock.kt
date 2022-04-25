package com.ocjadan.exhibitandroid.questions.views

import android.view.View
import com.ocjadan.exhibitandroid.questions.Question

class QuestionsViewControllerMock : QuestionsViewController {
    private val map = HashSet<QuestionsViewController.Listener>()

    override fun showQuestions(questions: List<Question>) {
        TODO("Not yet implemented")
    }

    override fun getListeners(): Set<QuestionsViewController.Listener> {
        return map.toSet()
    }

    override fun addListener(listener: QuestionsViewController.Listener) {
        this.map.add(listener)
    }

    override fun removeListener(listener: QuestionsViewController.Listener) {
        this.map.remove(listener)
    }

    override fun getRootView(): View {
        TODO("Not yet implemented")
    }
}