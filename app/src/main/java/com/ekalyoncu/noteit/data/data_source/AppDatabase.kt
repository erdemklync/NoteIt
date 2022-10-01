package com.ekalyoncu.noteit.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ekalyoncu.noteit.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "note_it.db"
    }
}