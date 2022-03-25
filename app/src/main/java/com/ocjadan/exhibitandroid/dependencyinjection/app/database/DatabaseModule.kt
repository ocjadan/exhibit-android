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
import dagger.Module
import dagger.Provides

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
    fun getQuestionsCache(questionsDao: QuestionsDao) = QuestionsCache(questionsDao)

    // Owners
    @Provides
    fun getOwnersDao(stackOverflowDb: StackOverflowDatabase) = stackOverflowDb.ownersDao

    @Provides
    fun getOwnersCache(ownersDao: OwnersDao) = OwnersCache(ownersDao)

    // Updates
    @Provides
    fun getUpdatesDao(stackOverflowDb: StackOverflowDatabase) = stackOverflowDb.updatesDao

    @Provides
    fun updatesCache(updatesDao: UpdatesDao) = UpdatesCache(updatesDao)
}