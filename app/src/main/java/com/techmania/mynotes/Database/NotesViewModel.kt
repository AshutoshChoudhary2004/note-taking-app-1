package com.techmania.mynotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NotesViewModel(application: Application) : AndroidViewModel(application){

    val repository : NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }
    fun addNotes (notes: Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.insertNotes(notes)
    }
    fun deleteNotes(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>>{
        return repository.getAllNotes()
    }
    fun updateNotes(notes: Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.updateNotes(notes)
    }
    fun getDsaNotes() : LiveData<List<Notes>>{
        return repository.getDsaNotes()
    }
    fun getDevNotes() : LiveData<List<Notes>>{
        return repository.getDevNotes()
    }
    fun getCollegeNotes() : LiveData<List<Notes>>{
        return repository.getCollegeNotes()
    }
}