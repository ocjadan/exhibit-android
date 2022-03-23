package com.ocjadan.exhibitandroid.questions.questionsList.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListItemView
import com.ocjadan.exhibitandroid.questions.questionsList.items.QuestionsListItem

@Composable
internal fun QuestionsListView(
    questionsListItems: List<QuestionsListItem>,
    onQuestionClicked: (questionsListItem: QuestionsListItem) -> Unit
) {
    if (questionsListItems.isEmpty()) {
        Text("No questions")
    } else {
        LazyColumn {
            items(questionsListItems) {
                QuestionsListItemView(Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp), it, onQuestionClicked)
            }
        }
    }
}