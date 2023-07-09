package com.techmania.mynotes

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
class Notes(
    var title: String,
    var notes: String,
    var date: String,
    var priority: String
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
