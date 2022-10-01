package com.ekalyoncu.noteit.presentation.ui.notes

import com.ekalyoncu.noteit.domain.model.Note

data class NotesDataState(
    val uiState: NoteUiState = NoteUiState.Loading,
    val notes: List<Note> = emptyList(),
)