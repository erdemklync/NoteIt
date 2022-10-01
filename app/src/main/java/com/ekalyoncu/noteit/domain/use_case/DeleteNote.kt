package com.ekalyoncu.noteit.domain.use_case

import com.ekalyoncu.noteit.domain.model.Note
import com.ekalyoncu.noteit.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNote @Inject constructor(
    private val noteRepository: NoteRepository
){
    suspend operator fun invoke(note: Note){
        noteRepository.deleteNote(note)
    }
}