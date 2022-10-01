package com.ekalyoncu.noteit.presentation.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekalyoncu.noteit.domain.use_case.NoteUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel(){

    private var _state = MutableStateFlow(NotesDataState())
    val state = _state.asStateFlow()

    init {
        getNotes()
    }

    private fun getNotes() = viewModelScope.launch {
        noteUseCases.getNotes().collectLatest { notes ->
            _state.update {
                it.copy(
                    notes = notes,
                    uiState = if(notes.isEmpty()) NoteUiState.NoData else NoteUiState.Loaded
                )
            }
        }
    }

}