package com.ekalyoncu.noteit.presentation.ui.add_edit

import com.ekalyoncu.noteit.domain.model.Note

data class AddEditDataState(
    val editMode: Boolean = false,
    val note: Note = Note(),
)
