package com.ocjadan.exhibitandroid.database.updates.lastQuestionsUpdate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(tableName = "last_questions_update", primaryKeys = ["id"], indices = [Index(value = ["id"], unique = true)])
data class LastQuestionsUpdateEntity(
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long
)