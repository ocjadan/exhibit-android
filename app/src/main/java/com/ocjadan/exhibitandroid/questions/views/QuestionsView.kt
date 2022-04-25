package com.ocjadan.exhibitandroid.questions.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ocjadan.exhibitandroid.R
import com.ocjadan.exhibitandroid.common.ui.Toolbar
import com.ocjadan.exhibitandroid.common.ui.theme.ExhibitAndroidTheme
import com.ocjadan.exhibitandroid.questions.Question

@Composable
internal fun QuestionsView(
    questions: List<Question>,
    onQuestionClicked: (question: Question) -> Unit,
    onAvatarClicked: () -> Unit
) {
    ExhibitAndroidTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Column {
                ComposeToolbar(onAvatarClicked)
                ComposeQuestions(questions, onQuestionClicked)
            }
        }
    }
}

@Composable
private fun ComposeToolbar(onAvatarClicked: () -> Unit) {
    Toolbar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 12.dp),
        startIcon = R.drawable.ic_baseline_account_circle_24,
        startIconDescription = R.string.toolbar_account_button,
        title = R.string.toolbar_app_title,
        onStartIconClicked = onAvatarClicked
    )
}

@Composable
private fun ComposeQuestions(questions: List<Question>, onQuestionClicked: (question: Question) -> Unit) {
    if (questions.isEmpty())
        Text("No questions")

    LazyColumn {
        items(questions) {
            QuestionsListItemView(Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp), it, onQuestionClicked)
        }
    }
}