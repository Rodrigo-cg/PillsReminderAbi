package com.abi.pillsreminderabi.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abi.pillsreminderabi.domain.model.HorarioPastilla
import kotlinx.coroutines.flow.Flow

interface HorarioRepository {
    fun getNotes(): Flow<List<HorarioPastilla>>

    suspend fun getNoteById(id:Int):HorarioPastilla?

    suspend fun insertNote(horarioPastilla: HorarioPastilla)

    suspend fun deleteNote(horarioPastilla: HorarioPastilla)
}