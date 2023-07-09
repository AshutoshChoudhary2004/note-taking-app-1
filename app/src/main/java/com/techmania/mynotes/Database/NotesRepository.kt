package com.techmania.mynotes

import androidx.lifecycle.LiveData

class NotesRepository(val dao : NotesDao) {
    suspend fun insertNotes(notes : Notes){
        dao.insertNotes(notes)
    }

    suspend fun deleteNotes(notes : Notes){
        dao.deleteNotes(notes)
    }

    suspend fun updateNotes(notes : Notes){
        dao.updateNotes(notes)
    }
    fun getAllNotes() : LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun getDsaNotes() : LiveData<List<Notes>>{
        return dao.getDsaNotes()
    }
    fun getDevNotes() : LiveData<List<Notes>>{
        return dao.getDevNotes()
    }
    fun getCollegeNotes() : LiveData<List<Notes>>{
        return dao.getCollegeNotes()
    }
}