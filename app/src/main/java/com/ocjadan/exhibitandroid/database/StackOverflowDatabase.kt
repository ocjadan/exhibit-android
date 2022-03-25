package com.ocjadan.exhibitandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ocjadan.exhibitandroid.database.owners.OwnerEntity
import com.ocjadan.exhibitandroid.database.owners.OwnersDao
import com.ocjadan.exhibitandroid.database.questions.QuestionEntity
import com.ocjadan.exhibitandroid.database.questions.QuestionsDao
import com.ocjadan.exhibitandroid.database.updates.lastQuestionsUpdate.LastQuestionsUpdateEntity
import com.ocjadan.exhibitandroid.database.updates.UpdatesDao

@Database(
    entities = [QuestionEntity::class, OwnerEntity::class, LastQuestionsUpdateEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StackOverflowDatabase : RoomDatabase() {
    abstract val ownersDao: OwnersDao
    abstract val questionsDao: QuestionsDao
    abstract val updatesDao: UpdatesDao
}