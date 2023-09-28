package com.abi.pillsreminderabi.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abi.pillsreminderabi.domain.model.HorarioPastilla

@Database(
    entities =[HorarioPastilla::class],
    version = 1
)
abstract class PillsHorarioDatabase: RoomDatabase() {
    abstract val noteDao:PillsHorarioDao

    companion object {
        const val DATABASE_NAME="PillsHorario_db"
    }
}