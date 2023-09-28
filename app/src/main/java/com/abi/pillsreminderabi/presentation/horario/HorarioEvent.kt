package com.abi.pillsreminderabi.presentation.horario

import com.abi.pillsreminderabi.domain.model.HorarioPastilla
import com.abi.pillsreminderabi.domain.util.HorarioOrder

sealed class HorarioEvent {
    data class Order(val noteOrder: HorarioOrder): HorarioEvent()
    data class DeleteNote(val note: HorarioPastilla): HorarioEvent()
    object RestoreNote: HorarioEvent()
    object ToggleOrderSection: HorarioEvent()
}