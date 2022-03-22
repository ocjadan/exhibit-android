package com.ocjadan.benchmarkable.questionDetails

import android.view.View

interface IQuestionDetailsViewController {
    interface Listener {

    }

    fun bindQuestionDetailsItem(item: QuestionDetailsItem)

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