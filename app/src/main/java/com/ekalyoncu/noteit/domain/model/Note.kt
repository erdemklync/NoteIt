package com.ekalyoncu.noteit.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ekalyoncu.noteit.util.containerColors
import com.ekalyoncu.noteit.util.onContainerColors
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey val id: Long = System.currentTimeMillis(),
    val title: String = "",
    val text: String = "",
    val color: Int = 0,
):Parcelable {
    fun getContainerColorValue(): Int {
        return containerColors[color]
    }
    fun getOnContainerColorValue(): Int {
        return onContainerColors[color]
    }
}
