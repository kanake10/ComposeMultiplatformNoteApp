package com.example.noteapp.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import app.cash.sqldelight.db.SqlDriver
import com.example.noteapp.domain.model.NotesDomain
import com.example.shared.data.cache.sqldelight.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class NotesDao (private val sqlDriver: SqlDriver) {
    private val appDatabase = AppDatabase(sqlDriver)
    val dbQuery = appDatabase.appDatabaseQueries

    suspend fun saveNote(notesDomain: NotesDomain) = withContext(Dispatchers.IO) {
        dbQuery.transaction {
            dbQuery.insert(
                title = notesDomain.title,
                description = notesDomain.description
            )
        }
    }

    fun getAllNotes() = dbQuery.getAll().asFlow().mapToList(Dispatchers.IO)

    fun getNote(id: Int) =
        dbQuery.getItemById(id = id.toLong()).asFlow().mapToOneOrNull(Dispatchers.IO)

    suspend fun deleteNote(id: Int) = withContext(Dispatchers.IO) {
        dbQuery.deleteNoteById(id = id.toLong())
    }

    suspend fun deleteAllNotes() = withContext(Dispatchers.IO) {
        dbQuery.deleteAllNotes()
    }
}