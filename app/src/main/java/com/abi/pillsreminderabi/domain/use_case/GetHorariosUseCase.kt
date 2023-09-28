package com.abi.pillsreminderabi.domain.use_case




import com.abi.pillsreminderabi.domain.model.HorarioPastilla
import com.abi.pillsreminderabi.domain.repository.HorarioRepository
import com.abi.pillsreminderabi.domain.util.HorarioOrder
import com.abi.pillsreminderabi.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHorariosUseCase (
    private val repository: HorarioRepository
){
    operator fun invoke(
        noteOrder: HorarioOrder =HorarioOrder.Date(OrderType.Descending)
    ): Flow<List<HorarioPastilla>>{
        return repository.getNotes().map{notes->
            when(noteOrder.orderType){
                is OrderType.Ascending ->{
                    when(noteOrder){
                        is HorarioOrder.Title ->notes.sortedBy { it.nombrePastilla.lowercase() }
                        is HorarioOrder.Date -> notes.sortedBy { it.timestamp }
                        is HorarioOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending ->{
                    when(noteOrder){
                        is HorarioOrder.Title ->notes.sortedByDescending { it.nombrePastilla.lowercase() }
                        is HorarioOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is HorarioOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}