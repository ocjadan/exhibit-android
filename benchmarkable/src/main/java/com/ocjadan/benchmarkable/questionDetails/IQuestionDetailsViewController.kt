package com.ocjadan.benchmarkable.questionDetails

import android.view.View

interface IQuestionDetailsViewController {
    fun bindDetails(details: QuestionDetails)
    fun bindAnswers(answers: List<QuestionAnswer>)

    /**
     * Difficult to move all code into benchmarkable module, reimplementing interfaces here
     */

    // Implement IViewController
    fun getRootView(): View
}