package com.ocjadan.exhibitandroid.questions.questionsList.view

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
import com.ocjadan.exhibitandroid.questions.Question
import com.ocjadan.exhibitandroid.questions.QuestionView
import com.ocjadan.exhibitandroid.common.ui.theme.ExhibitAndroidTheme

@Composable
internal fun QuestionsListView(questions: List<Question>, onQuestionClicked: (question: Question) -> Unit) {
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
                    title = R.string.toolbar_app_title) {
                }
                if (questions.isEmpty()) {
                    Text("No questions")
                } else {
                    LazyColumn {
                        items(questions) {
                            QuestionView(Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp), it, onQuestionClicked)
                        }
                    }
                }
            }
        }
    }
}