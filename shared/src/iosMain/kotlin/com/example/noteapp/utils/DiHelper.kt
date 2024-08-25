package com.example.noteapp.utils

import com.example.noteapp.di.commonModule
import com.example.noteapp.di.platformModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

actual class DiHelper {
    actual fun initKoin(): KoinApplication = startKoin {
        modules(commonModule, platformModule())
    }
}