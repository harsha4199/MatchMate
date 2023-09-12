package com.example.matchmate.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "local_db")
data class CardModelTransformation(
    val first: String,
    val last: String,
    val number: String,
    val country: String,
    val large: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}










