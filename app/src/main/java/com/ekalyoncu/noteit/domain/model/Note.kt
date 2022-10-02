package com.ekalyoncu.noteit.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ekalyoncu.noteit.util.colors

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey val id: Long,
    val title: String,
    val text: String,
    val color: Int,
){
    fun getColorValue(): Int {
        return colors[color]
    }
}
