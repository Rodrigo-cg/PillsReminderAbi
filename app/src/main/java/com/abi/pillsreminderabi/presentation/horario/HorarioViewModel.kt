package com.abi.pillsreminderabi.presentation.horario

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abi.pillsreminderabi.domain.model.HorarioPastilla
import com.abi.pillsreminderabi.domain.use_case.HorarioUseCases
import com.abi.pillsreminderabi.domain.util.HorarioOrder
import com.abi.pillsreminderabi.domain.util.OrderType

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HorarioViewModel @Inject constructor(
    private val noteUseCases: HorarioUseCases
) : ViewModel(){
    private val _state= mutableStateOf(HorarioState())
    val state: State<HorarioState> =_state

    private var recentlyDeletedNote: HorarioPastilla?=null

    private var getNotesJob: Job?=null

    init {
        getNotes(HorarioOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: HorarioEvent) {
        when (event) {
            is HorarioEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }
            is HorarioEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteHorarioUseCase(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is HorarioEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addHorarioUserCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is HorarioEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

            else -> {}
        }
    }
    private fun getNotes(noteOrder: HorarioOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getHorariosUseCase(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

}