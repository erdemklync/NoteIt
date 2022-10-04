package com.ekalyoncu.noteit.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekalyoncu.noteit.databinding.ItemNoteBinding
import com.ekalyoncu.noteit.domain.model.Note
import com.ekalyoncu.noteit.presentation.listener.NoteOnClickListener

class NoteAdapter(
    val listener: NoteOnClickListener
): ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffUtil()){

    inner class NoteViewHolder(
        private val binding: ItemNoteBinding
    ): RecyclerView.ViewHolder(binding.root){

        init {
            with(binding){
                root.setOnLongClickListener {
                    if(adapterPosition != RecyclerView.NO_POSITION){
                        val note = getItem(adapterPosition)
                        listener.onLongClick(note)
                    }

                    return@setOnLongClickListener true
                }
            }
        }

        fun bind(note: Note){
            with(binding){
                noteCard.setCardBackgroundColor(ContextCompat.getColor(root.context, note.getContainerColorValue()))
                noteTitle.setTextColor(ContextCompat.getColor(root.context, note.getOnContainerColorValue()))
                noteText.setTextColor(ContextCompat.getColor(root.context, note.getOnContainerColorValue()))

                noteTitle.text = note.title
                noteText.text = note.text

                if (noteTitle.text.isBlank()){
                    noteTitle.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }

    class NoteDiffUtil : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
    }
}