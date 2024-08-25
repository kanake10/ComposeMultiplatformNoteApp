package com.example.noteapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.model.NotesDomain
import com.example.noteapp.domain.repo.NotesRepository
import com.example.noteapp.utils.NotesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    private val _noteUiState = MutableStateFlow(NotesUiState())
    val notesUiState = _noteUiState.asStateFlow()

    init {
        getAllNotes()
    }

    fun getAllNotes() = viewModelScope.launch {
        try {
            notesRepository.getAllNotes().collectLatest {
                _noteUiState.value = _noteUiState.value.copy(isLoading = false, notes = it)
            }
        } catch (e: Exception) {
            _noteUiState.value =
                _noteUiState.value.copy(isLoading = false, errorMessage = e.message)
        }
    }

    fun deleteAllNotes() = viewModelScope.launch {
        try {
            notesRepository.deleteAllNotes()
        } catch (e: Exception) {
            _noteUiState.value =
                _noteUiState.value.copy(isLoading = false, errorMessage = e.message)
        }
    }

    fun deleteNote(id: Int) = viewModelScope.launch {
        try {
            notesRepository.deleteNote(id)
        } catch (e: Exception) {
            _noteUiState.value =
                _noteUiState.value.copy(isLoading = false, errorMessage = e.message)
        }
    }

    fun saveNote(notesDomain: NotesDomain) = viewModelScope.launch {
        try {
            notesRepository.saveNote(notesDomain = notesDomain)
        } catch (e: Exception) {
            _noteUiState.update { it.copy(errorMessage = e.message) }
        }
    }
}