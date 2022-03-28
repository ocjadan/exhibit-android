package com.ocjadan.exhibitandroid.database.questions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Query
import com.ocjadan.exhibitandroid.database.questions.questionWithOwner.QuestionWithOwnerEntity

@Dao
interface QuestionsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(questions: List<QuestionEntity>)

    @Transaction
    @Query("SELECT * FROM questions WHERE question_id = :id")
    fun getWithOwner(id: Long): QuestionWithOwnerEntity

    @Transaction
    @Query("SELECT * FROM questions ORDER BY tableOrderId DESC LIMIT :amount")
    fun getAllWithOwners(amount: Int): List<QuestionWithOwnerEntity>
}
