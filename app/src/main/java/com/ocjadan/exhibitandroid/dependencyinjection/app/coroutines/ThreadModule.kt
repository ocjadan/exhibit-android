package com.ocjadan.exhibitandroid.dependencyinjection.app.coroutines

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object ThreadModule {
    // If performance bottleneck then create an unbounded dispatcher
    @Provides
    @DispatcherBackground
    fun dispatcherBackground(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @DispatcherIO
    fun dispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @DispatcherUI
    fun dispatcherUI(): CoroutineDispatcher = Dispatchers.Main.immediate
}