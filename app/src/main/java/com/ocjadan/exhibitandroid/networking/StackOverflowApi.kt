package com.ocjadan.exhibitandroid.networking

import com.ocjadan.exhibitandroid.questions.QuestionData

interface StackOverflowApi {
    fun getQuestions(): List<QuestionData>
}
