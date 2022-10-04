package com.ekalyoncu.noteit.data.repository

import com.ekalyoncu.noteit.data.data_source.NoteDao
import com.ekalyoncu.noteit.domain.model.Note
import com.ekalyoncu.noteit.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao,
): NoteRepository{

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}