package com.ocjadan.exhibitandroid.common.viewcontroller

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ocjadan.benchmarkable.questionDetails.IQuestionDetailsViewController
import com.ocjadan.benchmarkable.questionDetails.QuestionDetailsViewController
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawerViewController
import com.ocjadan.exhibitandroid.common.screensNavigator.ScreensNavigator
import com.ocjadan.exhibitandroid.questions.questionsList.views.IQuestionsListViewController
import com.ocjadan.exhibitandroid.questions.questionsList.views.QuestionsListViewController

class ViewControllerFactory(private val layoutInflater: LayoutInflater, private val screensNavigator: ScreensNavigator) {
    fun getQuestionsListViewController(parent: ViewGroup?): IQuestionsListViewController {
        return QuestionsListViewController(layoutInflater, parent)
    }

    fun getNavDrawerViewController(parent: ViewGroup?): NavDrawerViewController {
        return NavDrawerViewController(layoutInflater, parent, screensNavigator)
    }

    fun getQuestionDetailsViewController(parent: ViewGroup?): IQuestionDetailsViewController {
        return QuestionDetailsViewController(layoutInflater, parent)
    }
}