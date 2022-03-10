package com.ocjadan.exhibitandroid.dependencyinjection.activity

import com.ocjadan.exhibitandroid.questions.QuestionsListActivity
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class, ViewModelModule::class])
interface ActivityComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ActivityComponent
    }

    fun inject(activity: QuestionsListActivity)
}