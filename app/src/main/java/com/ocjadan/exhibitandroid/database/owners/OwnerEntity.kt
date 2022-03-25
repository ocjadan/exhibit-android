package com.ocjadan.exhibitandroid.database.owners

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "owners", primaryKeys = ["owner_id"])
data class OwnerEntity(
    @ColumnInfo(name = "owner_id")
    val ownerId: Long,

    @ColumnInfo(name = "display_name")
    val name: String,

    @ColumnInfo(name = "profile_image")
    val image: String,

    @ColumnInfo(name = "account_id")
    val accountId: Long? = null
)