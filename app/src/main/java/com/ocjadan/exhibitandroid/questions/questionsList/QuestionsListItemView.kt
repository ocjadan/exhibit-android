package com.ocjadan.exhibitandroid.questions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListItem

@Composable
internal fun QuestionsListItemView(
    modifier: Modifier,
    questionsListItem: QuestionsListItem,
    onClick: (questionsListItem: QuestionsListItem) -> Unit
) {
    Row(
        modifier
            .clickable { onClick(questionsListItem) }) {
        Text(questionsListItem.title)
    }
}