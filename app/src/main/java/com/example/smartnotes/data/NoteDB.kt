package com.example.smartnotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDB : RoomDatabase() {
    abstract fun createDao(): NoteDAO

    companion object {
        fun getDB(context: Context): NoteDB {
            return Room.databaseBuilder(
                context.applicationContext,
                NoteDB::class.java,
                "notes"
            ).build()
        }
    }
}