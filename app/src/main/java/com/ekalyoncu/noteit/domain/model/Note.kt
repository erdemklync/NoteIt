package com.ekalyoncu.noteit.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ekalyoncu.noteit.util.colors
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey val id: Long = System.currentTimeMillis(),
    val title: String = "",
    val text: String = "",
    val color: Int = 0,
):Parcelable {
    fun getColorValue(): Int {
        return colors[color]
    }
}
