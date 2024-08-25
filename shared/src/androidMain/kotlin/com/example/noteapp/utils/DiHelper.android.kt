package com.example.noteapp.utils


import com.example.noteapp.di.commonModule
import com.example.noteapp.di.platformModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

actual class DiHelper(val appDeclaration: KoinAppDeclaration = {}) {
    actual fun initKoin(): KoinApplication = startKoin {
        appDeclaration()
        modules(commonModule, platformModule())
    }
}