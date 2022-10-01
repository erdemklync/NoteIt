package com.ekalyoncu.noteit.data.data_source

import androidx.room.Dao
import androidx.room.Query
import com.ekalyoncu.noteit.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<Note>>
}