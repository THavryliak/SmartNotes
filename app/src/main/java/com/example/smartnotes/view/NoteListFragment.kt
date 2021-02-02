package com.example.smartnotes.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.smartnotes.R
import com.example.smartnotes.adapter.Adapter
import com.example.smartnotes.adapter.MyClickListener
import com.example.smartnotes.data.Note
import com.example.smartnotes.viewModel.NoteListViewModel
import com.example.smartnotes.viewModel.NoteViewModel


class NoteListFragment : Fragment(R.layout.fragment_note_list), MyClickListener {
    private lateinit var model: NoteListViewModel
    private var adapter = Adapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(NoteListViewModel::class.java)

        val notesList = view.findViewById<RecyclerView>(R.id.noteList)
        val plusImage = view.findViewById<ImageView>(R.id.plus_image)

        plusImage.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_createNoteFragment)
        }

        notesList.adapter = adapter

        model.notesList.observe(viewLifecycleOwner) {
            setData(it)
        }
        swipeToDelete(model, notesList)
    }


    private fun setData(data: List<Note>) {
        adapter.submitList(data)
    }

    private fun swipeToDelete(model: NoteListViewModel, myList: RecyclerView) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                model.deleteNote(adapter.currentList[viewHolder.adapterPosition])
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(myList)
    }

    override fun onItemClicked(note: Note) {
        val model = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)
        findNavController().navigate(R.id.action_noteListFragment_to_noteFragment)
        model.setNoteTxt(note.title)
        model.setNoteDate(note.date)
    }
}