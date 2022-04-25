package com.ocjadan.exhibitandroid.questions.questionsList.views

import com.ocjadan.exhibitandroid.common.viewcontroller.ObservableViewController
import com.ocjadan.exhibitandroid.questions.questionsList.Question

interface IQuestionsListViewController : ObservableViewController<IQuestionsListViewController.Listener> {
    interface Listener {
        fun onQuestionClicked(question: Question)
        fun onToolbarAvatarClicked()
    }

    fun bindQuestions(questions: List<Question>)
}