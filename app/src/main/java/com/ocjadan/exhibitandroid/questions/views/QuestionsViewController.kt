package com.ocjadan.exhibitandroid.questions.views

import com.ocjadan.exhibitandroid.common.viewcontroller.ObservableViewController
import com.ocjadan.exhibitandroid.questions.Question

interface QuestionsViewController : ObservableViewController<QuestionsViewController.Listener> {
    interface Listener {
        fun onQuestionClicked(question: Question)
        fun onToolbarAvatarClicked()
    }

    fun showQuestions(questions: List<Question>)
}