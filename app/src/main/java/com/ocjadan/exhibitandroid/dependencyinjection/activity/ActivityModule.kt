package com.ocjadan.exhibitandroid.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.ocjadan.exhibitandroid.common.navdrawer.NavDrawer
import com.ocjadan.exhibitandroid.common.screensNavigator.NavigationHelper
import com.ocjadan.exhibitandroid.common.screensNavigator.NavigationController
import com.ocjadan.exhibitandroid.common.screensNavigator.ScreensNavigator
import com.ocjadan.exhibitandroid.common.viewcontroller.ViewControllerFactory
import com.ocjadan.exhibitandroid.common.viewmodel.ViewModelFactory
import com.ocjadan.exhibitandroid.questions.questionDetails.QuestionDetailsViewModel
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
internal object ActivityModule {
    @Provides
    fun viewModelFactory(
        questionsListVM: Provider<QuestionsListViewModel>,
        questionDetailsVM: Provider<QuestionDetailsViewModel>
    ) = ViewModelFactory(questionsListVM, questionDetailsVM)

    @Provides
    fun viewControllerFactory(layoutInflater: LayoutInflater, screensNavigator: ScreensNavigator) =
        ViewControllerFactory(layoutInflater, screensNavigator)

    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    fun fragmentManager(activity: AppCompatActivity): FragmentManager = activity.supportFragmentManager

    @Provides
    fun navDrawer(activity: AppCompatActivity): NavDrawer = activity as NavDrawer

    @Provides
    fun screensNavigator(navigationHelper: NavigationHelper) = ScreensNavigator(navigationHelper)

    @Provides
    fun navigationHelper(activity: AppCompatActivity): NavigationHelper = activity as NavigationHelper

    @Provides
    fun navigationController(fragmentManager: FragmentManager) = NavigationController(fragmentManager)
}