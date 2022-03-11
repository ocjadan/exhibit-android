package com.ocjadan.exhibitandroid

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListViewController

class ViewControllerFactory(private val layoutInflater: LayoutInflater) {
    fun getQuestionsListViewController(container: ViewGroup?): QuestionsListViewController {
        return QuestionsListViewController(layoutInflater, container)
    }
}