package com.example.noteapp.domain.repo

import com.example.noteapp.domain.model.NotesDomain
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun saveNote(notesDomain: NotesDomain)

    suspend fun getAllNotes(): Flow<List<NotesDomain>>

    suspend fun getNote(id: Int): Flow<NotesDomain?>

    suspend fun deleteNote(id: Int)

    suspend fun deleteAllNotes()
}