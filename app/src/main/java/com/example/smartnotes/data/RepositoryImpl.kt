package com.example.smartnotes.data

import androidx.lifecycle.LiveData

class RepositoryImpl(private val noteDao: NoteDAO) {

    val noteList: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun addNote(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun removeNote(note: Note) {
        noteDao.deleteNote(note)
    }
}