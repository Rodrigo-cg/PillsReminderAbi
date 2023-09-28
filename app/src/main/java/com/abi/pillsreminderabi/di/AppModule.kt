package com.abi.pillsreminderabi.di

import android.app.Application
import androidx.room.Room
import com.abi.pillsreminderabi.data.data_source.PillsHorarioDatabase
import com.abi.pillsreminderabi.data.repository.HorarioPastillaRepositoryImpl
import com.abi.pillsreminderabi.domain.repository.HorarioRepository
import com.abi.pillsreminderabi.domain.use_case.AddHorarioUserCase
import com.abi.pillsreminderabi.domain.use_case.DeleteHorarioUseCase
import com.abi.pillsreminderabi.domain.use_case.GetHorarioUseCase
import com.abi.pillsreminderabi.domain.use_case.GetHorariosUseCase
import com.abi.pillsreminderabi.domain.use_case.HorarioUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): PillsHorarioDatabase {
        return Room.databaseBuilder(
            app,
            PillsHorarioDatabase::class.java,
            PillsHorarioDatabase.DATABASE_NAME

        ).build()
    }
    @Provides
    @Singleton
    fun provideNoteRepository(db:PillsHorarioDatabase): HorarioRepository {
        return HorarioPastillaRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: HorarioRepository): HorarioUseCases {
        return HorarioUseCases(
            getHorarioUseCase = GetHorarioUseCase(repository),
            deleteHorarioUseCase = DeleteHorarioUseCase(repository),
            addHorarioUserCase = AddHorarioUserCase(repository),
            getHorariosUseCase = GetHorariosUseCase(repository)
        )
    }
}