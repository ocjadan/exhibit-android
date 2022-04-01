package com.ocjadan.exhibitandroid.dependencyinjection.app.database

import android.content.Context
import androidx.room.Room
import com.ocjadan.exhibitandroid.database.StackOverflowDatabase
import com.ocjadan.exhibitandroid.database.owners.OwnersCache
import com.ocjadan.exhibitandroid.database.owners.OwnersDao
import com.ocjadan.exhibitandroid.database.questions.QuestionsDao
import com.ocjadan.exhibitandroid.dependencyinjection.app.AppScope
import com.ocjadan.exhibitandroid.database.questions.QuestionsCache
import com.ocjadan.exhibitandroid.database.updates.UpdatesCache
import com.ocjadan.exhibitandroid.database.updates.UpdatesDao
import com.ocjadan.exhibitandroid.dependencyinjection.app.coroutines.DispatcherBackground
import com.ocjadan.exhibitandroid.dependencyinjection.app.coroutines.DispatcherIO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
internal object DatabaseModule {
    @AppScope
    @Provides
    fun getStackOverflowDb(context: Context): StackOverflowDatabase {
        return Room.databaseBuilder(context, StackOverflowDatabase::class.java, "stackoverflow_database")
            .fallbackToDestructiveMigration() // TODO: proper migration
            .build()
    }

    // Questions
    @Provides
    fun getQuestionsDao(stackOverflowDb: StackOverflowDatabase) = stackOverflowDb.questionsDao

    @Provides
    fun getQuestionsCache(questionsDao: QuestionsDao, @DispatcherIO dispatcher: CoroutineDispatcher) =
        QuestionsCache(questionsDao, dispatcher)

    // Owners
    @Provides
    fun getOwnersDao(stackOverflowDb: StackOverflowDatabase) = stackOverflowDb.ownersDao

    @Provides
    fun getOwnersCache(ownersDao: OwnersDao, @DispatcherIO dispatcher: CoroutineDispatcher) =
        OwnersCache(ownersDao, dispatcher)

    // Updates
    @Provides
    fun getUpdatesDao(stackOverflowDb: StackOverflowDatabase) = stackOverflowDb.updatesDao

    @Provides
    fun updatesCache(updatesDao: UpdatesDao, @DispatcherIO dispatcher: CoroutineDispatcher) =
        UpdatesCache(updatesDao, dispatcher)
}