package com.ocjadan.exhibitandroid.questions.questionsList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ocjadan.exhibitandroid.questions.Question
import com.ocjadan.exhibitandroid.questions.QuestionView
import com.ocjadan.exhibitandroid.ui.theme.ExhibitAndroidTheme

@Composable
internal fun QuestionsListView(questions: List<Question>, onQuestionClicked: (id: Int) -> Unit) {
    ExhibitAndroidTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
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