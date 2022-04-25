package com.ocjadan.exhibitandroid.dependencyinjection.fragment

import com.ocjadan.exhibitandroid.questionDetails.QuestionDetailsFragment
import com.ocjadan.exhibitandroid.questions.QuestionsFragment
import dagger.Subcomponent

@Subcomponent
interface FragmentComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): FragmentComponent
    }

    fun inject(fragment: QuestionsFragment)
    fun inject(fragment: QuestionDetailsFragment)
}