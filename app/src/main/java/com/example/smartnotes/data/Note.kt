package com.example.smartnotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "myNotes"
)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date: String
)