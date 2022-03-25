package com.ocjadan.exhibitandroid.database.owners

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface OwnersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // User already exists, do nothing
    fun insertAll(owners: List<OwnerEntity>)
}
