package com.ocjadan.exhibitandroid.questions.questionsList.items

import com.ocjadan.exhibitandroid.owners.Owner
import java.io.Serializable

data class QuestionsListItem(val id: Int, val title: String, val owner: Owner, val isAnswered: Boolean) : Serializable