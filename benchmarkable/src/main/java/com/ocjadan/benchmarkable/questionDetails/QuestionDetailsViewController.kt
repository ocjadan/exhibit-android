package com.ocjadan.benchmarkable.questionDetails

import android.view.View

interface QuestionDetailsViewController {
    fun show(details: QuestionDetails, answers: List<QuestionAnswer>)

    /**
     * Difficult to move all code into benchmarkable module, reimplementing interfaces below.
     */

    fun getRootView(): View
}