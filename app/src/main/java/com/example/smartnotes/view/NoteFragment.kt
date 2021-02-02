package com.example.smartnotes.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartnotes.R
import com.example.smartnotes.viewModel.NoteViewModel

class NoteFragment : Fragment(R.layout.fragment_note) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val noteText = view.findViewById<TextView>(R.id.noteText)
        val noteDate = view.findViewById<TextView>(R.id.note_date)
        val model = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        model.noteDt.observe(viewLifecycleOwner) {
            noteDate.text = it
        }

        model.noteTxt.observe(viewLifecycleOwner) {
            noteText.text = it
        }
    }
}