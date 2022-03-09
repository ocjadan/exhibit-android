package com.ocjadan.exhibitandroid.dependencyinjection.activity

import dagger.Subcomponent

@Subcomponent
interface ActivityComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ActivityComponent
    }
}