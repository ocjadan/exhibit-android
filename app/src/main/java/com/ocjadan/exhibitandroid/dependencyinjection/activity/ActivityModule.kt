package com.ocjadan.exhibitandroid.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.ocjadan.exhibitandroid.common.NavDrawerHelper
import com.ocjadan.exhibitandroid.common.viewcontroller.ViewControllerFactory
import com.ocjadan.exhibitandroid.common.viewmodel.ViewModelFactory
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListController
import com.ocjadan.exhibitandroid.questions.questionsList.QuestionsListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
object ActivityModule {
    @Provides
    fun viewModelFactory(questionsListVM: Provider<QuestionsListViewModel>) = ViewModelFactory(questionsListVM)

    @Provides
    fun viewControllerFactory(layoutInflater: LayoutInflater, fragmentManager: FragmentManager) =
        ViewControllerFactory(layoutInflater, fragmentManager)

    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    fun fragmentManager(activity: AppCompatActivity): FragmentManager = activity.supportFragmentManager

    @Provides
    fun questionsListController(navDrawerHelper: NavDrawerHelper): QuestionsListController =
        QuestionsListController(navDrawerHelper)

    @Provides
    fun getNavDrawerHelper(activity: AppCompatActivity): NavDrawerHelper = activity as NavDrawerHelper
}