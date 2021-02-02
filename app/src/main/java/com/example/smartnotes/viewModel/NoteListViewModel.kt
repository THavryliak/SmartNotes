package com.example.smartnotes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.smartnotes.data.Note
import com.example.smartnotes.data.NoteDB
import com.example.smartnotes.data.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteListViewModel(app: Application) : AndroidViewModel(app) {

    val notesList: LiveData<List<Note>>
    private val repository: RepositoryImpl

    init {
        val noteDao = NoteDB.getDB(app).createDao()
        repository = RepositoryImpl(noteDao)
        notesList = repository.noteList
    }

    fun addNote(st: String, nd: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(Note(0, st, nd))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeNote(note)
        }
    }
}