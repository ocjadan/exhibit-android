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

enum class QuestionDetailsViewState {
    IDLE, LOADING, ANSWERS, NO_ANSWERS
}

@Composable
fun QuestionDetailsView(details: QuestionDetails, answers: List<QuestionAnswer>, state: QuestionDetailsViewState) {
    LazyColumn {
        item {
            Row {
                Text("QuestionDetailsView")
            }
        }

        when (state) {
            QuestionDetailsViewState.IDLE -> Unit
            QuestionDetailsViewState.LOADING -> {
                item {
                    Text("Loading")
                }
            }
            QuestionDetailsViewState.ANSWERS -> {
                items(answers) {
                    AnswerView(Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp), it)
                }
            }
            QuestionDetailsViewState.NO_ANSWERS -> {
                item {
                    Text("No answers")
                }
            }
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