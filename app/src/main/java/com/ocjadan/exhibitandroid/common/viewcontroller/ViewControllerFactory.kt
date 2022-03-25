package com.ocjadan.exhibitandroid.common.viewcontroller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentManager
import com.ocjadan.benchmarkable.questionDetails.IQuestionDetailsViewController
import com.ocjadan.benchmarkable.questionDetails.QuestionDetailsViewController
import com.ocjadan.exhibitandroid.common.navdrawer.INavDrawerViewController
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawerViewController
import com.ocjadan.exhibitandroid.questions.questionsList.views.IQuestionsListViewController
import com.ocjadan.exhibitandroid.questions.questionsList.views.QuestionsListViewController

class ViewControllerFactory(private val layoutInflater: LayoutInflater, private val fragmentManager: FragmentManager) {
    fun getQuestionsListViewController(parent: ViewGroup?): IQuestionsListViewController {
        return QuestionsListViewController(layoutInflater, parent)
    }

    fun getNavDrawerViewController(parent: ViewGroup?, @NavigationRes navigationGraph: Int): INavDrawerViewController {
        return NavDrawerViewController(layoutInflater, fragmentManager, parent, navigationGraph)
    }

    fun getQuestionDetailsViewController(parent: ViewGroup?): IQuestionDetailsViewController {
        return QuestionDetailsViewController(layoutInflater, parent)
    }
}