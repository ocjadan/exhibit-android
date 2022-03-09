package com.ocjadan.exhibitandroid.questions

object QuestionsTestData {
    private const val TITLE = "TITLE"

    fun questionData(): List<QuestionData> {
        return listOf(QuestionData(TITLE))
    }
}