package com.ocjadan.exhibitandroid.questions.questionsList.view

import com.ocjadan.exhibitandroid.common.viewcontroller.IBaseObservableViewController
import com.ocjadan.exhibitandroid.questions.Question

interface IQuestionsListViewController : IBaseObservableViewController<IQuestionsListViewController.Listener> {
    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    fun bindQuestions(questions: List<Question>)
}