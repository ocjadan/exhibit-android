package com.ocjadan.exhibitandroid.questions.questionsList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.ocjadan.exhibitandroid.R
import com.ocjadan.exhibitandroid.common.ui.Toolbar
import com.ocjadan.exhibitandroid.common.ui.theme.ExhibitAndroidTheme
import com.ocjadan.exhibitandroid.common.viewcontroller.BaseObservableViewController
import com.ocjadan.exhibitandroid.questions.Question

class QuestionsListViewController(layoutInflater: LayoutInflater, viewGroup: ViewGroup?) : IQuestionsListViewController,
    BaseObservableViewController<IQuestionsListViewController.Listener>(layoutInflater, viewGroup, R.layout.compose_view) {

    private var questions: List<Question> = emptyList()

    override fun bindQuestions(questions: List<Question>) {
        this.questions = questions
        compose()
    }

    private fun compose() {
        val composeView = getRootView() as? ComposeView ?: return
        composeView.setContent {
            ExhibitAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column {
                        Toolbar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp, 12.dp),
                            startIcon = R.drawable.ic_baseline_account_circle_24,
                            startIconDescription = R.string.toolbar_account_button,
                            title = R.string.toolbar_app_title,
                            onStartIconClicked = ::onAvatarClicked
                        )
                        QuestionsListView(questions, ::onQuestionClicked)
                    }
                }
            }
        }
    }

    private fun onQuestionClicked(question: Question) {
        for (listener in getListeners()) {
            listener.onQuestionClicked(question)
        }
    }

    private fun onAvatarClicked() {
        for (listener in getListeners()) {
            listener.onToolbarAvatarClicked()
        }
    }
}