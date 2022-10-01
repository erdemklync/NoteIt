package com.ekalyoncu.noteit.presentation.listener

import com.ekalyoncu.noteit.domain.model.Note

interface NoteListener {
    fun onLongClick(note: Note): Boolean
}