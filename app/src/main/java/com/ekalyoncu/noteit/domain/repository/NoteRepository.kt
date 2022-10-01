package com.ekalyoncu.noteit.domain.repository

import com.ekalyoncu.noteit.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
}