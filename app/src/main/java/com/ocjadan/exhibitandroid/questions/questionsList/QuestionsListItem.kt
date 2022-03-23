package com.ocjadan.exhibitandroid.questions.questionsList

import com.ocjadan.exhibitandroid.users.Owner
import java.io.Serializable

data class QuestionsListItem(val id: Int, val title: String, val owner: Owner, val isAnswered: Boolean) : Serializable