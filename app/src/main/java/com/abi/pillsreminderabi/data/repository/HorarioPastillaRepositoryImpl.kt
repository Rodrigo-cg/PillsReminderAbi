package com.abi.pillsreminderabi.data.repository



import com.abi.pillsreminderabi.data.data_source.PillsHorarioDao
import com.abi.pillsreminderabi.domain.model.HorarioPastilla
import com.abi.pillsreminderabi.domain.repository.HorarioRepository
import kotlinx.coroutines.flow.Flow

class HorarioPastillaRepositoryImpl(
    private val dao: PillsHorarioDao
): HorarioRepository {
    override fun getNotes(): Flow<List<HorarioPastilla>> {
        return dao.getHorarios()
    }

    override suspend fun getNoteById(id: Int): HorarioPastilla? {
        return  dao.getHorarioById(id)
    }

    override suspend fun insertNote(note: HorarioPastilla) {
        return dao.insertHorario(note)
    }

    override suspend fun deleteNote(note: HorarioPastilla) {
        return dao.deleteHorario(note)
    }
}