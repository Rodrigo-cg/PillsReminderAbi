package com.abi.pillsreminderabi.presentation.add_edit_Horario

import androidx.compose.ui.focus.FocusState

sealed class AddEditHorarioEvent{

    data class EnteredNombrePastilla(val value: String): AddEditHorarioEvent()
    data class ChangeNombrePastillaFocus(val focusState: FocusState): AddEditHorarioEvent()

    data class EnteredDescripcionPastilla(val value: String): AddEditHorarioEvent()
    data class ChangeDescripcionPastillaFocus(val focusState: FocusState): AddEditHorarioEvent()

    data class ChangeColor(val color: Int): AddEditHorarioEvent()

    data class EnteredCantidadPastillasTotales(val value: String): AddEditHorarioEvent()
    data class ChangeCantidadPastillasTotalesFocus(val focusState: FocusState): AddEditHorarioEvent()

    data class EnteredPastillasPorDia(val value: String) : AddEditHorarioEvent()
    data class ChangePastillasPorDiaFocus(val focusState: FocusState) : AddEditHorarioEvent()



    data class EnteredDiasPastillas(val value: String) : AddEditHorarioEvent()
    data class ChangeDiasPastillasFocus(val focusState: FocusState) : AddEditHorarioEvent()


    object SaveNote: AddEditHorarioEvent()
}