package com.ocjadan.exhibitandroid.common.viewcontrollers

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ocjadan.exhibitandroid.questions.questionsList.view.IQuestionsListViewController
import com.ocjadan.exhibitandroid.questions.questionsList.view.QuestionsListViewController

class ViewControllerFactory(private val layoutInflater: LayoutInflater) {
    fun getQuestionsListViewController(parent: ViewGroup?): IQuestionsListViewController {
        return QuestionsListViewController(layoutInflater, parent)
    }
}