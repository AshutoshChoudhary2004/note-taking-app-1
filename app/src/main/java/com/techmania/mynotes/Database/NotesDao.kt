package com.techmania.mynotes

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table")
    fun getNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM notes_table WHERE priority=3")
    fun getDsaNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM notes_table WHERE priority=2")
    fun getDevNotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM notes_table WHERE priority=1")
    fun getCollegeNotes() : LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertNotes(note : Notes)

    @Delete
     suspend fun deleteNotes(note : Notes)

    @Update
     suspend fun updateNotes(note : Notes)



}