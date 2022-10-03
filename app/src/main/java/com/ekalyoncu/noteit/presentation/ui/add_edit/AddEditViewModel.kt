package com.ekalyoncu.noteit.presentation.ui.add_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekalyoncu.noteit.domain.use_case.NoteUseCases
import com.ekalyoncu.noteit.presentation.ui.notes.NotesDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
): ViewModel(){

    private var _state = MutableStateFlow(AddEditDataState())
    val state = _state.asStateFlow()

    fun insertEntry() = viewModelScope.launch {
        noteUseCases.insertNote(state.value.note)
    }

    fun setTitle(title: String){
        _state.update {
            it.copy(
                note = it.note.copy(
                    title = title
                )
            )
        }
    }

    fun setText(text: String){
        _state.update {
            it.copy(
                note = it.note.copy(
                    text = text
                )
            )
        }
    }

}