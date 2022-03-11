package com.ocjadan.exhibitandroid.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.ocjadan.exhibitandroid.ViewControllerFactory
import com.ocjadan.exhibitandroid.ViewModelFactory
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
    fun layoutInflater(activity: AppCompatActivity) = LayoutInflater.from(activity)
}