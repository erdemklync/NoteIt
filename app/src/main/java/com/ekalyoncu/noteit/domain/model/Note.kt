package com.ekalyoncu.noteit.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey val id: Long,
    val text: String,
    val color: Int,
)
