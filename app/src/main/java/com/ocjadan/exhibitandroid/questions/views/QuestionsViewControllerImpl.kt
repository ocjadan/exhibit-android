package com.ocjadan.exhibitandroid.questions.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.ocjadan.exhibitandroid.R
import com.ocjadan.exhibitandroid.common.viewcontroller.BaseObservableViewController
import com.ocjadan.exhibitandroid.questions.Question
import java.lang.RuntimeException

class QuestionsViewControllerImpl(layoutInflater: LayoutInflater, viewGroup: ViewGroup?) : QuestionsViewController,
    BaseObservableViewController<QuestionsViewController.Listener>(layoutInflater, viewGroup, R.layout.compose_view) {

    override fun showQuestions(questions: List<Question>) {
        composeQuestions(questions)
    }

    private fun composeQuestions(questions: List<Question>) {
        getComposeView().setContent { QuestionsView(questions, ::onQuestionClicked, ::onAvatarClicked) }
    }

    private fun getComposeView(): ComposeView {
        return getRootView() as? ComposeView ?: throw RuntimeException("Questions view is not a ComposeView.")
    }

    private fun onQuestionClicked(question: Question) {
        for (listener in getListeners())
            listener.onQuestionClicked(question)
    }

    private fun onAvatarClicked() {
        for (listener in getListeners())
            listener.onToolbarAvatarClicked()
    }
}