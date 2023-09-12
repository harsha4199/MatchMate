package com.example.matchmate.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.matchmate.model.CardModelTransformation

@Dao
interface ResultsDao {
    @Query("SELECT * FROM local_db")
    fun getAllResult(): List<CardModelTransformation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(results: CardModelTransformation)

    @Delete
    suspend fun deleteResult(results: CardModelTransformation)
}