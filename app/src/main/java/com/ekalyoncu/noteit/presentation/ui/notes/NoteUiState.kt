package com.ekalyoncu.noteit.presentation.ui.notes

sealed class NoteUiState{
    object Loading: NoteUiState()
    object Loaded: NoteUiState()
    object NoData: NoteUiState()
}
