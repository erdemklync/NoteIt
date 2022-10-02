package com.ekalyoncu.noteit.data.repository

import com.ekalyoncu.noteit.data.data_source.NoteDao
import com.ekalyoncu.noteit.domain.model.Note
import com.ekalyoncu.noteit.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao,
): NoteRepository{

    private val fakeData = listOf(
        Note(
            id = 0,
            title = "Başlık",
            text = "Not",
            color = 0
        ),
        Note(
            id = 1,
            title = "Başlık",
            text = "Not",
            color = 1
        ),
        Note(
            id = 2,
            title = "Başlık",
            text = "Not",
            color = 2
        ),
    )

    override fun getNotes(): Flow<List<Note>> {
        //return noteDao.getNotes()
        return flow { emit(fakeData) }
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}