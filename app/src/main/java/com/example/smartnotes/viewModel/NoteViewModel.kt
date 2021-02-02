package com.example.smartnotes.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {

    val noteTxt = MutableLiveData<String>()

    val noteDt = MutableLiveData<String>()

    fun setNoteTxt(text: String) {
        noteTxt.value = text
    }

    fun setNoteDate(date: String) {
        noteDt.value = date
    }
}