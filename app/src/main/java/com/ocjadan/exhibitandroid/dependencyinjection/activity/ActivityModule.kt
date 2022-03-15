package com.ocjadan.exhibitandroid.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.ocjadan.exhibitandroid.common.viewcontrollers.ViewControllerFactory
import com.ocjadan.exhibitandroid.common.ViewModelFactory
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
    fun viewControllerFactory(layoutInflater: LayoutInflater) = ViewControllerFactory(layoutInflater)

    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    fun questionsListController(): QuestionsListController = QuestionsListController()
}