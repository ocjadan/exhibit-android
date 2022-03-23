package com.ocjadan.benchmarkable.questionDetails

import android.view.View
import com.ocjadan.benchmarkable.answers.Answer

interface IQuestionDetailsViewController {
    fun bindDetails(details: QuestionDetails)
    fun bindAnswers(answers: List<Answer>)

    /**
     * Difficult to move all code into benchmarkable module, reimplementing interfaces here
     */

    // Implement IViewController
    fun getRootView(): View
}