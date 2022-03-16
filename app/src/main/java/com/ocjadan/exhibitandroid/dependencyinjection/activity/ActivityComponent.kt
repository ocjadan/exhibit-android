package com.ocjadan.exhibitandroid.dependencyinjection.activity

import androidx.appcompat.app.AppCompatActivity
import com.ocjadan.exhibitandroid.MainActivity
import com.ocjadan.exhibitandroid.dependencyinjection.fragment.FragmentComponent
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class, ViewModelModule::class])
interface ActivityComponent {
    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder
        fun build(): ActivityComponent
    }

    fun fragmentComponentBuilder(): FragmentComponent.Builder

    fun inject(activity: MainActivity)
}