package com.ocjadan.exhibitandroid.questions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.ocjadan.exhibitandroid.R

class QuestionsListViewController(layoutInflater: LayoutInflater, viewGroup: ViewGroup?) {

    val rootView: View = layoutInflater.inflate(R.layout.compose_view, viewGroup, false)

    fun bindQuestions(questions: List<Question>) {
        (rootView as ComposeView).setContent {
            QuestionsListView(questions)
        }
    }

    fun bindError(error: QuestionsListViewModel.QuestionsListError) {

    }
}