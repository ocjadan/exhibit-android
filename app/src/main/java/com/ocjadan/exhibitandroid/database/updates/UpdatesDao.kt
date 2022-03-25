package com.ocjadan.exhibitandroid.database.updates

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ocjadan.exhibitandroid.database.updates.lastQuestionsUpdate.LastQuestionsUpdateEntity

@Dao
interface UpdatesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setLastQuestionsUpdate(entity: LastQuestionsUpdateEntity)

    @Query("SELECT * FROM last_questions_update LIMIT 1")
    fun getLastQuestionsUpdate(): LastQuestionsUpdateEntity?
}