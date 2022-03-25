package com.ocjadan.exhibitandroid.database.questions.questionWithOwner

import androidx.room.Embedded
import androidx.room.Relation
import com.ocjadan.exhibitandroid.database.owners.OwnerEntity
import com.ocjadan.exhibitandroid.database.questions.QuestionEntity

data class QuestionWithOwner(
    @Embedded val question: QuestionEntity,
    @Relation(
        parentColumn = "owner_id",
        entityColumn = "owner_id"
    )
    val owner: OwnerEntity,
)