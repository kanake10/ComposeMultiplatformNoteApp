package com.example.noteapp.utils

import com.example.noteapp.domain.model.NotesDomain

data class NotesUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val notes: List<NotesDomain>? = emptyList()
)
