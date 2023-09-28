package com.abi.pillsreminderabi.domain.use_case

import com.abi.pillsreminderabi.domain.model.HorarioPastilla
import com.abi.pillsreminderabi.domain.repository.HorarioRepository


class GetHorarioUseCase(
    private val repository: HorarioRepository
) {

    suspend operator fun invoke(id: Int): HorarioPastilla? {
        return repository.getNoteById(id)
    }
}