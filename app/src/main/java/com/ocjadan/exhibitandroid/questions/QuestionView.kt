package com.ocjadan.exhibitandroid.questions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun QuestionView(modifier: Modifier, question: Question, onClick: (question: Question) -> Unit) {
    Row(
        modifier
            .clickable { onClick(question) }) {
        Text(question.title)
    }
}