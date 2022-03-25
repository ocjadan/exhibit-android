package com.ocjadan.exhibitandroid.questions.questionsList.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ocjadan.exhibitandroid.questions.questionsList.Question

@Composable
internal fun QuestionsListItemView(
    modifier: Modifier,
    question: Question,
    onClick: (question: Question) -> Unit
) {
    Column(modifier
        .clickable { onClick(question) }) {
        Row {
            Text(question.title)
        }
        Row {
            if (question.isAnswered) {
                Text("Answered", color = Color.Green)
            } else {
                Text("Be the first to answer!", color = Color.Magenta)
            }
        }
    }
}