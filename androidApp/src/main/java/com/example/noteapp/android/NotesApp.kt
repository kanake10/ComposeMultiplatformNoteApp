package com.example.noteapp.android

import android.app.Application
import com.example.noteapp.utils.DiHelper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class NotesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DiHelper(appDeclaration = {
            androidLogger(level = Level.DEBUG)
            androidContext(this@NotesApp)
        }).initKoin()
    }
}