package com.example.noteapp.data

import com.example.noteapp.data.cache.sqldelight.NoteEntity
import com.example.noteapp.domain.model.NotesDomain

fun NoteEntity.toDomain(): NotesDomain {
    return NotesDomain(
        id = id.toInt(),
        title = title,
        description =  description
    )
}