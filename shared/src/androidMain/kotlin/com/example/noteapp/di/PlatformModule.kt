package com.example.noteapp.di


import com.example.noteapp.utils.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory(context = get()).createDriver() }
}