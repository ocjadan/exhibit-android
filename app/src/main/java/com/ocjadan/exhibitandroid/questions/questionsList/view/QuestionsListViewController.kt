package com.ocjadan.exhibitandroid.questions.questionsList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.ocjadan.exhibitandroid.R
import com.ocjadan.exhibitandroid.common.viewcontrollers.BaseObservableViewController
import com.ocjadan.exhibitandroid.questions.Question

class QuestionsListViewController(layoutInflater: LayoutInflater, viewGroup: ViewGroup?) : IQuestionsListViewController,
    BaseObservableViewController<IQuestionsListViewController.Listener>(layoutInflater, viewGroup, R.layout.compose_view) {

    private var questions: List<Question> = emptyList()

    override fun bindQuestions(questions: List<Question>) {
        this.questions = questions
        compose()
    }

    private fun compose() {
        (getRootView() as ComposeView).setContent {
            QuestionsListView(questions, ::onQuestionClicked)
        }
    }

    private fun onQuestionClicked(question: Question) {
        for (listener in listenersMap) {
            listener.onQuestionClicked(question)
        }
    }
}