package com.example.noteapp.data.repoimpl


import com.example.noteapp.data.NotesDao
import com.example.noteapp.data.toDomain
import com.example.noteapp.domain.model.NotesDomain
import com.example.noteapp.domain.repo.NotesRepository
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(private val notesDao: NotesDao) : NotesRepository {

    override suspend fun saveNote(notesDomain: NotesDomain) =
        notesDao.saveNote(notesDomain)

    override suspend fun getAllNotes() =
        notesDao.getAllNotes().map { it.map { notesDomain -> notesDomain.toDomain() } }

    override suspend fun getNote(id: Int) =
        notesDao.getNote(id).map { it?.toDomain() }

    override suspend fun deleteNote(id: Int) = notesDao.deleteNote(id)

    override suspend fun deleteAllNotes() = notesDao.deleteAllNotes()
}