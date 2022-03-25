package com.ocjadan.exhibitandroid.questions.questionsList.views

import com.ocjadan.exhibitandroid.common.viewcontroller.IObservableViewController
import com.ocjadan.exhibitandroid.questions.questionsList.Question

interface IQuestionsListViewController : IObservableViewController<IQuestionsListViewController.Listener> {
    interface Listener {
        fun onQuestionClicked(question: Question)
        fun onToolbarAvatarClicked()
    }

    fun bindQuestions(questions: List<Question>)
}