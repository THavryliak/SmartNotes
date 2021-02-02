package com.example.smartnotes.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.smartnotes.R
import com.example.smartnotes.viewModel.NoteListViewModel
import java.text.SimpleDateFormat
import java.util.*


class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {

    companion object {
        const val RCSI = 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val micro = view.findViewById<ImageView>(R.id.micro_image)
        micro.setOnClickListener {
            recognizer()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val model = ViewModelProvider(requireActivity()).get(NoteListViewModel::class.java)

        @SuppressLint("SimpleDateFormat")
        val sdf = SimpleDateFormat("dd.M.yyyy HH:mm")
        if (requestCode == RCSI && resultCode == Activity.RESULT_OK) {
            val res: List<String>? = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            model.addNote(res?.get(0).toString().capitalize(), sdf.format(Date()))
            findNavController().navigate(R.id.action_createNoteFragment_to_noteListFragment)
        }
    }

    private fun recognizer() {
        val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        i.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak.")
        startActivityForResult(i, RCSI)
    }
}