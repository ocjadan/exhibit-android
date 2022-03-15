package com.ocjadan.exhibitandroid.questions.questionsList.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ocjadan.exhibitandroid.questions.Question
import com.ocjadan.exhibitandroid.questions.QuestionView

@Composable
internal fun QuestionsListView(questions: List<Question>, onQuestionClicked: (question: Question) -> Unit) {
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