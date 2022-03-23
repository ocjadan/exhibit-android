package com.ocjadan.benchmarkable.questionDetails

import android.view.View
import com.ocjadan.benchmarkable.answers.Answer

interface IQuestionDetailsViewController {
    interface Listener {

    }

    fun bindQuestionDetails(details: QuestionDetails)
    fun bindAnswers(answers: List<Answer>)

    /**
     * Difficult to move all code into benchmarkable module, reimplementing interfaces here
     */

    // IViewController
    fun getRootView(): View

    // IObservable
    fun addListener(listener: Listener)
    fun removeListener(listener: Listener)
    fun getListeners(): Set<Listener>
}