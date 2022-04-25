package com.ocjadan.exhibitandroid.common.viewcontroller

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ocjadan.benchmarkable.questionDetails.QuestionDetailsViewController
import com.ocjadan.benchmarkable.questionDetails.QuestionDetailsViewControllerImpl
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawerViewController
import com.ocjadan.exhibitandroid.common.screensNavigator.ScreensNavigator
import com.ocjadan.exhibitandroid.questions.views.QuestionsViewController
import com.ocjadan.exhibitandroid.questions.views.QuestionsViewControllerImpl

class ViewControllerFactory(private val layoutInflater: LayoutInflater, private val screensNavigator: ScreensNavigator) {
    fun getQuestionsListViewController(parent: ViewGroup?): QuestionsViewController {
        return QuestionsViewControllerImpl(layoutInflater, parent)
    }

    fun getNavDrawerViewController(parent: ViewGroup?): NavDrawerViewController {
        return NavDrawerViewController(layoutInflater, parent, screensNavigator)
    }

    fun getQuestionDetailsViewController(parent: ViewGroup?): QuestionDetailsViewController {
        return QuestionDetailsViewControllerImpl(layoutInflater, parent)
    }
}