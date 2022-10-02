package com.ekalyoncu.noteit.domain.use_case

import com.ekalyoncu.noteit.domain.model.Note
import com.ekalyoncu.noteit.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotes @Inject constructor(
    private val noteRepository: NoteRepository
){
    operator fun invoke(): Flow<List<Note>> {
        return noteRepository.getNotes()
    }
}