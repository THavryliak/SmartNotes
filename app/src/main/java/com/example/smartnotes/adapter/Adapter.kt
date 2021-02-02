package com.example.smartnotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smartnotes.R
import com.example.smartnotes.data.Note


class Adapter(
    private val itemClickListener: MyClickListener
) : ListAdapter<Note, Adapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val date: TextView = view.findViewById(R.id.card_date)
        private val title: TextView = view.findViewById(R.id.note_title)

        fun bind(note: Note, clickListener: MyClickListener) {
            date.text = note.date
            title.text = note.title.substringBefore(" ").capitalize().plus("...")
            itemView.setOnClickListener {
                clickListener.onItemClicked(note)
            }
        }
    }
}

interface MyClickListener {
    fun onItemClicked(note: Note)
}