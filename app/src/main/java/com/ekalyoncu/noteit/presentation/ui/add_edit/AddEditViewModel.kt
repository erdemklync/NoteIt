package com.ekalyoncu.noteit.presentation.ui.add_edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekalyoncu.noteit.R
import com.ekalyoncu.noteit.domain.model.Note
import com.ekalyoncu.noteit.domain.use_case.NoteUseCases
import com.ekalyoncu.noteit.util.NOTE_ARGS_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle,
): ViewModel(){

    private var _state = MutableStateFlow(AddEditDataState())
    val state get() = _state.asStateFlow()

    val toolbarTitle: Int
        get() = if(state.value.editMode) R.string.title_edit else R.string.title_add

    init {
        savedStateHandle.get<Note>(NOTE_ARGS_ID)?.let { note ->
            _state.update {
                it.copy(
                    editMode = true,
                    note = note,
                )
            }
        }
    }

    fun insertNote() = viewModelScope.launch {
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

    fun setColor(color: Int){
        _state.update {
            it.copy(
                note = it.note.copy(
                    color = color
                )
            )
        }
    }
}