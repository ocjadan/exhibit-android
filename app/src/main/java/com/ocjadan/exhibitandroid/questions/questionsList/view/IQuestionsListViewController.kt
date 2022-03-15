package com.ocjadan.exhibitandroid.questions.questionsList.view

import com.ocjadan.exhibitandroid.common.viewcontroller.IObservableViewController
import com.ocjadan.exhibitandroid.questions.Question

interface IQuestionsListViewController : IObservableViewController<IQuestionsListViewController.Listener> {
    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    fun bindQuestions(questions: List<Question>)
}