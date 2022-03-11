package com.ocjadan.exhibitandroid.questions.questionsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.ocjadan.exhibitandroid.common.BaseObservable
import com.ocjadan.exhibitandroid.R
import com.ocjadan.exhibitandroid.questions.Question

class QuestionsListViewController(layoutInflater: LayoutInflater, viewGroup: ViewGroup?) :
    BaseObservable<QuestionsListViewController.Listener>() {

    interface Listener {
        fun onQuestionClicked(id: Int)
    }

    val rootView: View = layoutInflater.inflate(R.layout.compose_view, viewGroup, false)

    fun bindQuestions(questions: List<Question>) {
        (rootView as ComposeView).setContent {
            QuestionsListView(questions, ::onQuestionClicked)
        }
    }

    fun bindError(error: QuestionsListViewModel.QuestionsListError) {

    }

    private fun onQuestionClicked(id: Int) {
        for (listener in listenersMap) {
            listener.onQuestionClicked(id)
        }
    }
}