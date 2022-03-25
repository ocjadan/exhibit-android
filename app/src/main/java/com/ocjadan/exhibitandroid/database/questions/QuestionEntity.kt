package com.ocjadan.exhibitandroid.database.questions

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "questions", primaryKeys = ["question_id"])
data class QuestionEntity(
    @ColumnInfo(name = "question_id")
    val questionId: Long,

    @ColumnInfo(name = "is_answered")
    val isAnswered: Boolean,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "creation_date")
    val creationDate: Long,

    @ColumnInfo(name = "owner_id")
    val ownerId: Long
)