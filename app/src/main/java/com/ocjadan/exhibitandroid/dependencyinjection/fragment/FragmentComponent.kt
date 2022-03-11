package com.ocjadan.exhibitandroid.dependencyinjection.fragment

import com.ocjadan.exhibitandroid.questions.QuestionsListFragment
import dagger.Subcomponent

@Subcomponent
interface FragmentComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): FragmentComponent
    }

    fun inject(fragment: QuestionsListFragment)
}