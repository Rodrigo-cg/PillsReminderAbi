package com.abi.pillsreminderabi.presentation.horario

import com.abi.pillsreminderabi.domain.model.HorarioPastilla
import com.abi.pillsreminderabi.domain.util.HorarioOrder
import com.abi.pillsreminderabi.domain.util.OrderType

data class HorarioState(
    val notes:List<HorarioPastilla> = emptyList(),
    val noteOrder: HorarioOrder =HorarioOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean=false

)