package com.abi.pillsreminderabi.domain.use_case

import com.abi.pillsreminderabi.domain.model.HorarioPastilla
import com.abi.pillsreminderabi.domain.repository.HorarioRepository


class DeleteHorarioUseCase (
    private  val repository: HorarioRepository
){
    suspend operator fun invoke(note: HorarioPastilla){
        repository.deleteNote(note)
    }
}