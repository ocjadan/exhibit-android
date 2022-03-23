package com.ocjadan.exhibitandroid.questions.questionsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ocjadan.exhibitandroid.questions.questionsList.items.QuestionsListItem

@Composable
internal fun QuestionsListItemView(
    modifier: Modifier,
    questionsListItem: QuestionsListItem,
    onClick: (questionsListItem: QuestionsListItem) -> Unit
) {
    Column(modifier
        .clickable { onClick(questionsListItem) }) {
        Row {
            Text(questionsListItem.title)
        }
        Row {
            if (questionsListItem.isAnswered) {
                Text("Answered", color = Color.Green)
            } else {
                Text("Be the first to answer!", color = Color.Magenta)
            }
        }
    }
}