package com.ocjadan.benchmarkable.questionDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun QuestionDetailsView(details: QuestionDetails, answers: List<QuestionAnswer>) {
    if (!details.isAnswered) {
        Text("Question currently has no answers.")
        return
    }

    if (answers.isEmpty())
        Text("Loading...")

    LazyColumn {
        items(answers) {
            AnswerView(Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp), it)
        }
    }
}

@Composable
private fun AnswerView(modifier: Modifier, answer: QuestionAnswer) {
    Column(modifier) {
        Row {
            Text("Answer:", color = Color.Blue)
        }
        Row {
            Text(answer.body)
        }
    }
}