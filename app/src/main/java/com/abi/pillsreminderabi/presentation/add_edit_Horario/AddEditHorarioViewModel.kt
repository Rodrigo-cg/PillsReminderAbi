package com.abi.pillsreminderabi.presentation.add_edit_Horario

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.abi.pillsreminderabi.domain.model.HorarioPastilla
import com.abi.pillsreminderabi.domain.model.InvalidNoteException
import com.abi.pillsreminderabi.domain.use_case.HorarioUseCases


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditHorarioViewModel @Inject constructor(
    private val noteUseCases: HorarioUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///Nombre de la pastilla
    private val _noteNombrePastilla = mutableStateOf(HorarioTextFieldState(
        hint = "Digite el nombre de la pastilla"
    ))
    val noteNombrePastilla: State<HorarioTextFieldState> = _noteNombrePastilla

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///Descripcion de la pastilla
    private val _noteDescripcionPastilla = mutableStateOf(HorarioTextFieldState(
        hint = "Digite la descripcion para que tratamiento es la pastilla"
    ))
    val noteContent: State<HorarioTextFieldState> = _noteDescripcionPastilla

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///Cantidad de pastillas a tomar por dia
    private val _noteCantidadPastillaPorDia = mutableStateOf(HorarioTextFieldState(
        hint = "Digite la cantidad de las pastillas a tomar por dia"
    ))
    val noteCantidadPastillaPorDia : State<HorarioTextFieldState> = _noteCantidadPastillaPorDia

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///Cantidad de Dias que requiere tomar las pastillas
    private val _noteDiasPastilla = mutableStateOf(HorarioTextFieldState(
        hint = "Digite cuantos dias debe tomar la pastilla"
    ))
    val noteDiasPastilla : State<HorarioTextFieldState> = _noteDiasPastilla

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///Cantidad total de Pastillas que quedan tomar
    private val _noteTotalPastillasActuales = mutableStateOf(HorarioTextFieldState(
        hint = "Digite cuantos dias debe tomar la pastilla"
    ))
    val noteTotalPastillasActuales : State<HorarioTextFieldState> = _noteTotalPastillasActuales

    ////////////////////////////////////////////////////////////////////////////////////////////////

    ///Horarios generados
    private val _noteHorariosGenerados = mutableStateOf(HorarioTextFieldState(
        hint = "Digite cuantos dias debe tomar la pastilla"
    ))
    val noteHorariosGenerados : State<HorarioTextFieldState> = _noteHorariosGenerados

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private val _noteColor = mutableStateOf(HorarioPastilla.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getHorarioUseCase(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteNombrePastilla.value = noteNombrePastilla.value.copy(
                            text = note.nombrePastilla,
                            isHintVisible = false
                        )
                        _noteDescripcionPastilla.value = _noteDescripcionPastilla.value.copy(
                                text = note.descripcionPastilla,
                        isHintVisible = false
                        )
                        _noteTotalPastillasActuales.value = _noteTotalPastillasActuales.value.copy(
                            text = note.cantidadPastillasTotales,
                            isHintVisible = false
                        )
                        _noteCantidadPastillaPorDia.value = _noteCantidadPastillaPorDia.value.copy(
                            text = note.cantidadPastillasPorDia,
                            isHintVisible = false
                        )
                        _noteDiasPastilla.value = _noteDiasPastilla.value.copy(
                            text = note.diasPastillas,
                            isHintVisible = false
                        )
                        _noteDescripcionPastilla.value = _noteDescripcionPastilla.value.copy(
                            text = note.descripcionPastilla,
                            isHintVisible = false
                        )
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditHorarioEvent) {
        when(event) {
            is AddEditHorarioEvent.EnteredNombrePastilla -> {
                _noteNombrePastilla.value = noteNombrePastilla.value.copy(
                    text = event.value
                )
            }
            is AddEditHorarioEvent.ChangeNombrePastillaFocus -> {
                _noteNombrePastilla.value = noteNombrePastilla.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteNombrePastilla.value.text.isBlank()
                )
            }
            is AddEditHorarioEvent.EnteredDescripcionPastilla -> {
                _noteDescripcionPastilla.value = _noteDescripcionPastilla.value.copy(
                    text = event.value
                )
            }
            is AddEditHorarioEvent.ChangeDescripcionPastillaFocus -> {
                _noteDescripcionPastilla.value = _noteDescripcionPastilla.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _noteDescripcionPastilla.value.text.isBlank()
                )
            }
            is AddEditHorarioEvent.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AddEditHorarioEvent.EnteredCantidadPastillasTotales ->{
                _noteDescripcionPastilla.value = _noteDescripcionPastilla.value.copy(
                    text = event.value
                )
            }
            is AddEditHorarioEvent.EnteredCantidadPastillasTotales ->{
                _noteDescripcionPastilla.value = _noteDescripcionPastilla.value.copy(
                    text = event.value
                )
            }
            is AddEditHorarioEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addHorarioUserCase(
                            HorarioPastilla(
                                nombrePastilla = noteNombrePastilla.value.text,
                                descripcionPastilla = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId,
                                cantidadPastillasPorDia = "0",
                                horariosGeneradosPastilla = "hola",
                                diasPastillas = "2",
                                cantidadPastillasTotales = "5"
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch(e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }

            else -> {}
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }
}