package com.ekalyoncu.noteit.domain.use_case

import javax.inject.Inject

data class NoteUseCases @Inject constructor(
    val getNotes: GetNotes,
    val insertNote: InsertNote,
    val deleteNote: DeleteNote,
)