package com.example.noteapp.di

import org.koin.dsl.module
import com.example.noteapp.utils.DatabaseDriverFactory

actual fun platformModule() = module {
    single { DatabaseDriverFactory().createDriver() }
}