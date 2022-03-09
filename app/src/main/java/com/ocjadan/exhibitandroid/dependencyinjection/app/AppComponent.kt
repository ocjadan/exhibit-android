package com.ocjadan.exhibitandroid.dependencyinjection.app

import android.app.Application
import com.ocjadan.exhibitandroid.dependencyinjection.activity.ActivityComponent
import com.ocjadan.exhibitandroid.dependencyinjection.app.networking.NetworkModule
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun activityComponentBuilder(): ActivityComponent.Builder
}