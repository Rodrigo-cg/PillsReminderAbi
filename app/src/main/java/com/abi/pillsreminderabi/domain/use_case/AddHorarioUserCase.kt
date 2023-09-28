package com.abi.pillsreminderabi.domain.use_case

import com.abi.pillsreminderabi.domain.model.HorarioPastilla
import com.abi.pillsreminderabi.domain.model.InvalidNoteException
import com.abi.pillsreminderabi.domain.repository.HorarioRepository


class AddHorarioUserCase(
    private val repository: HorarioRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(horario: HorarioPastilla) {
        if(horario.nombrePastilla.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if(horario.horariosGeneradosPastilla.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        if(horario.cantidadPastillasPorDia.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        if(horario.diasPastillas.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        if(horario.descripcionPastilla.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(horario)
    }
}