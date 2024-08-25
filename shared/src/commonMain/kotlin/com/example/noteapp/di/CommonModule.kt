package com.example.noteapp.di

import com.example.noteapp.data.NotesDao
import com.example.noteapp.data.repoimpl.NotesRepositoryImpl
import com.example.noteapp.domain.repo.NotesRepository
import com.example.noteapp.ui.screens.NotesViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {

    single<NotesRepository> { NotesRepositoryImpl(notesDao = get()) }
    single { NotesDao(sqlDriver = get()) }
    viewModel { NotesViewModel(notesRepository = get()) }

}

expect fun platformModule(): Module